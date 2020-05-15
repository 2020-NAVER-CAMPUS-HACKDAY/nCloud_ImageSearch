package com.hackday.imageSearch.ui.viewer

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseActivity
import com.hackday.imageSearch.databinding.ActivityViewerBinding
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import kotlinx.android.synthetic.main.activity_viewer.*
import kotlinx.android.synthetic.main.dialog_viewer_infodetail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewerActivity : BaseActivity<ActivityViewerBinding>() {

    private lateinit var id: String
    private lateinit var uri: String

    override val vm: ViewerViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_viewer

    override fun setupBinding() {
        binding.vm = vm
    }

    val pvm = PhotoInfoViewModel(vm.repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadImage()
    }

    override fun onStart() {
        super.onStart()

        btn_back.setOnClickListener {
            finish()
        }

        btn_detail.setOnClickListener {
            loadDetail()
        }
    }

    fun loadImage(){
        // photoFragment 에서 전달되는 id, uri 받기
        if(intent != null && intent.hasExtra(EXTRA_PHOTO_ID) && intent.hasExtra(EXTRA_PHOTO_URI)){
            id = intent.getStringExtra(EXTRA_PHOTO_ID)!!
            uri = intent.getStringExtra(EXTRA_PHOTO_URI)!!

            Glide.with(this)
                .load(uri)
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions().fitCenter())
                .into(img_photo_detail)
        }
    }

    fun loadDetail(){
        // uri를 통해 photo 데이터 로드
        pvm.getPhotoByUri(uri)
        // dialog에 출력
        pvm.photoOne.observe(this, Observer {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_viewer_infodetail, null)
            d_photo_date.text = it.date
            d_photo_gps.text = it.gps.toString()
            d_photo_tag1.text = it.tag1
            d_photo_tag2.text = it.tag2
            d_photo_tag3.text = it.tag3

            builder.setView(dialogView).create().show()
        })

    }

    companion object{
        const val EXTRA_PHOTO_ID = "EXTRA_PHOTO_ID"
        const val EXTRA_PHOTO_DATE = "EXTRA_PHOTO_DATE"
        const val EXTRA_PHOTO_GPS = "EXTRA_PHOTO_GPS"
        const val EXTRA_PHOTO_URI = "EXTRA_PHOTO_URI"
        const val EXTRA_PHOTO_TAG1 = "EXTRA_PHOTO_TAG1"
        const val EXTRA_PHOTO_TAG2 = "EXTRA_PHOTO_TAG2"
        const val EXTRA_PHOTO_TAG3 = "EXTRA_PHOTO_TAG3"
    }
}

