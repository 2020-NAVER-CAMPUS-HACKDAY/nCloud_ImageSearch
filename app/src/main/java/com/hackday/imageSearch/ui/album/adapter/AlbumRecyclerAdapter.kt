package com.hackday.imageSearch.ui.album.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.R

class AlbumRecyclerAdapter : RecyclerView.Adapter<AlbumRecyclerAdapter.Holder>() {
    private lateinit var context: Context
    private var albumItemList = ArrayList<Int>() //sample


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.img_album_thumbnail)
        private val label = itemView.findViewById<TextView>(R.id.txt_album_label)

        fun bind(unit: Int) {
            image.setImageResource(unit)
            label.text = "장소"
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

    fun setItemList(itemList: ArrayList<Int>) {
        albumItemList = itemList
        notifyDataSetChanged()
    }
}