package com.hackday.imageSearch.ui.photo.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.R
import com.hackday.imageSearch.model.PhotoInfo

class PhotoRecyclerAdapter(val photoClick: (PhotoInfo)-> Unit) :
    RecyclerView.Adapter<PhotoRecyclerAdapter.Holder>() {
    lateinit var context: Context
    //private var photoItemList = ArrayList<Int>() //sample함
    private var photoItemList = ArrayList<PhotoInfo>()


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date = itemView.findViewById<TextView>(R.id.txt_date)
        private val image = itemView.findViewById<ImageView>(R.id.img_photo)

        fun bind(photo: PhotoInfo) {
            //date.text = "5월 12일 화요일" // sample

            // Glide

            itemView.setOnClickListener {
                photoClick(photo)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(photoItemList[position])
    }


    override fun getItemCount(): Int {
        return photoItemList.size
    }


    fun setItemList(itemList: ArrayList<PhotoInfo>) {
        photoItemList = itemList
    }

}