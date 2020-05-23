package com.hackday.imageSearch.ui.viewer

import android.app.AlertDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseActivity
import com.hackday.imageSearch.databinding.ActivityMainBinding
import com.hackday.imageSearch.databinding.ActivityViewerBinding
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import kotlinx.android.synthetic.main.activity_viewer.*
import kotlinx.android.synthetic.main.dialog_viewer_infodetail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.zip.Inflater

class ViewerActivity : AppCompatActivity() {

    val pvm: PhotoInfoViewModel by viewModel()

    private lateinit var uri: String
    private lateinit var dlg: DetailDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)

        dlg = DetailDialog(this)

        loadImage()
    }

    override fun onStart() {
        super.onStart()

        btn_back.setOnClickListener {
            finish()
        }

        btn_detail.setOnClickListener {
            dialogDetail()
        }

        btn_delete.setOnClickListener {
            deleteAll()
        }
    }

    fun loadImage() {
        // photoFragment 에서 전달되는 id, uri 받기
        if (intent != null && intent.hasExtra(EXTRA_PHOTO_URI)) {
            uri = intent.getStringExtra(EXTRA_PHOTO_URI)!!

            Glide.with(this)
                .load(uri)
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions().fitCenter())
                .into(img_photo_detail)

            pvm.getPhotoByUri(uri).observe(this, Observer {
                dlg.start(it)
            })

        }
    }

    fun dialogDetail() {
        dlg.show()
    }

    fun deleteAll() {
        pvm.deleteAll()
        finish()
    }

    companion object {
        const val EXTRA_PHOTO_DATE = "EXTRA_PHOTO_DATE"
        const val EXTRA_PHOTO_URI = "EXTRA_PHOTO_URI"
        const val EXTRA_PHOTO_TAG1 = "EXTRA_PHOTO_TAG1"
        const val EXTRA_PHOTO_TAG2 = "EXTRA_PHOTO_TAG2"
        const val EXTRA_PHOTO_TAG3 = "EXTRA_PHOTO_TAG3"
    }
}

