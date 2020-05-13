package com.hackday.imageSearch.ui.photo

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentPhotoBinding
import com.hackday.imageSearch.ui.photo.adapter.PhotoItemDecorator
import com.hackday.imageSearch.ui.photo.adapter.PhotoRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : BaseFragment<FragmentPhotoBinding>() {
    override val vm: PhotoViewModel by viewModel()
    override fun getLayoutRes() = R.layout.fragment_photo
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {

        val recyclerManager = GridLayoutManager(context!!, 4)

        with(layout_recycler_view) {
            adapter = PhotoRecyclerAdapter()
            layoutManager = recyclerManager
            setHasFixedSize(false)
            addItemDecoration(PhotoItemDecorator())
        }
    }

}