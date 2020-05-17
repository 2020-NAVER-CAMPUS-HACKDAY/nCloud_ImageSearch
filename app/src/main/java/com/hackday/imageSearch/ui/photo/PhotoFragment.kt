package com.hackday.imageSearch.ui.photo

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentPhotoBinding
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.ui.photo.adapter.PhotoItemDecorator
import com.hackday.imageSearch.ui.photo.adapter.PhotoRecyclerAdapter
import com.hackday.imageSearch.ui.viewer.ViewerActivity
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : BaseFragment<FragmentPhotoBinding>(), photoClickListener {
    override val vm: PhotoViewModel by viewModel()
    override fun getLayoutRes() = R.layout.fragment_photo
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.getAll()
        setRecyclerView()
    }

    private fun setRecyclerView() {

        val recyclerManager = GridLayoutManager(context!!, 4)

        with(layout_recycler_view) {
            adapter = PhotoRecyclerAdapter { photo ->
                photoClicked(photo)
            }
            layoutManager = recyclerManager
            setHasFixedSize(false)
            addItemDecoration(PhotoItemDecorator())
        }
    }

    override fun photoClicked(photo: PhotoInfo) {
        val intent = Intent(context, ViewerActivity::class.java)
        intent.putExtra(ViewerActivity.EXTRA_PHOTO_ID, photo.id)
        intent.putExtra(ViewerActivity.EXTRA_PHOTO_URI, photo.uri)
        startActivity(intent)
    }

}