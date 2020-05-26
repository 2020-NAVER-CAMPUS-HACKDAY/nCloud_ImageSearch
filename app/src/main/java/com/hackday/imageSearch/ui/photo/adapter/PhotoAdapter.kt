package com.hackday.imageSearch.ui.photo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackday.imageSearch.R
import com.hackday.imageSearch.model.PhotoInfo

class PhotoAdapter(val photoClick: (ImageView, Int, PagedList<PhotoInfo>?) -> Unit) :
    PagedListAdapter<PhotoInfo, PhotoAdapter.Holder>(DIFF_CALLBACK) {
    lateinit var context: Context

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date = itemView.findViewById<TextView>(R.id.txt_date)
        private val imageview = itemView.findViewById<ImageView>(R.id.img_photo)

        fun bind(photo: PhotoInfo?, pos: Int) {

            Glide.with(context)
                .load(photo?.uri)
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions().fitCenter().override(IMAGE_SIZE, IMAGE_SIZE))
                .into(imageview)

            date.text = photo?.date

            itemView.setOnClickListener {
                photoClick(imageview, pos, currentList)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val photo: PhotoInfo? = getItem(position)
        holder.bind(photo, position)
    }

    companion object {
        const val IMAGE_SIZE = 400

        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<PhotoInfo>() {
            override fun areItemsTheSame(
                oldConcert: PhotoInfo,
                newConcert: PhotoInfo
            ) = oldConcert.uri == newConcert.uri

            override fun areContentsTheSame(
                oldConcert: PhotoInfo,
                newConcert: PhotoInfo
            ) = oldConcert == newConcert
        }
    }
}