package com.hackday.imageSearch.ui.viewer

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.hackday.imageSearch.R
import com.hackday.imageSearch.model.PhotoInfo

class DetailDialog(context: Context) : Dialog(context) {

    fun start(photo : PhotoInfo) {
        this.setContentView(R.layout.dialog_viewer_infodetail)     //다이얼로그에 사용할 xml 파일을 불러옴
        this.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        val lbldate = this.findViewById<TextView>(R.id.d_photo_date)
        val lbltag1 = this.findViewById<TextView>(R.id.d_photo_tag1)
        val lbltag2 = this.findViewById<TextView>(R.id.d_photo_tag2)
        val lbltag3 = this.findViewById<TextView>(R.id.d_photo_tag3)
        lbldate.text = photo.date
        lbltag1.text = photo.tag1
        lbltag2.text = photo.tag2
        lbltag3.text = photo.tag3

        val btnOK = this.findViewById<Button>(R.id.btn_ok)
        btnOK.setOnClickListener {
            this.dismiss()
        }

        show()
    }
}