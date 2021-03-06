package com.hackday.imageSearch.ui.album.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackday.imageSearch.R
import com.hackday.imageSearch.database.model.PhotoTag

class AlbumAdapter : PagedListAdapter<PhotoTag, AlbumAdapter.Holder>(DIFF_CALLBACK) {
    private lateinit var context: Context
    private var item: OnItemClickListener? = null


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.img_album_thumbnail)
        private val label = itemView.findViewById<TextView>(R.id.txt_album_label)

        fun bind(photo: PhotoTag?) {

            Glide.with(context)
                .load(photo?.uri)
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions().fitCenter().override(IMAGE_SIZE, IMAGE_SIZE))
                .into(image)

            label.text = photo?.tag

            itemView.setOnClickListener {
                item?.clickItem(photo?.tag)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val album: PhotoTag? = getItem(position)
        holder.bind(album)
    }

    fun setOnItemClickListener(listener: (String?) -> Unit) {
        item = object : OnItemClickListener {
            override fun clickItem(label: String?) {
                listener(label)
            }
        }
    }

    interface OnItemClickListener {
        fun clickItem(label: String?)
    }

    companion object {
        const val IMAGE_SIZE = 400

        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<PhotoTag>() {
            override fun areItemsTheSame(
                oldConcert: PhotoTag,
                newConcert: PhotoTag
            ) = oldConcert.tag == newConcert.tag

            override fun areContentsTheSame(
                oldConcert: PhotoTag,
                newConcert: PhotoTag
            ) = oldConcert == newConcert
        }
    }
}