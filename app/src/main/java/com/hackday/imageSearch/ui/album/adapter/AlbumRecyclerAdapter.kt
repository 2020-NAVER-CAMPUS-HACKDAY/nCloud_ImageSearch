package com.hackday.imageSearch.ui.album.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackday.imageSearch.R
import com.hackday.imageSearch.ui.album.TagModel

class AlbumRecyclerAdapter : RecyclerView.Adapter<AlbumRecyclerAdapter.Holder>() {
    private lateinit var context: Context
    private lateinit var albumItemList: List<TagModel>


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.img_album_thumbnail)
        private val label = itemView.findViewById<TextView>(R.id.txt_album_label)

        fun bind(photo: TagModel) {

            Glide.with(context)
                .load(photo.thumbnail)
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions().fitCenter())
                .into(image)

            label.text = photo.tag
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(albumItemList[position])
    }

    override fun getItemCount(): Int {
        return albumItemList.size
    }

    fun setItemList(itemList: List<TagModel>) {
        albumItemList = itemList
        notifyDataSetChanged()
    }
}