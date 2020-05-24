package com.hackday.imageSearch.ui.photo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentPhotoBinding
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.ui.main.MainViewModel
import com.hackday.imageSearch.ui.photo.adapter.PhotoAdapter
import com.hackday.imageSearch.ui.photo.adapter.PhotoItemDecorator
import com.hackday.imageSearch.ui.viewer.ViewerActivity
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : BaseFragment<FragmentPhotoBinding>(), PhotoClickListener {
    override val vm: PhotoViewModel by viewModel()
    override fun getLayoutRes() = R.layout.fragment_photo
    override fun setupBinding() {
        binding.vm = vm
    }

    private val mvm by activityViewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        val recyclerManager = GridLayoutManager(context, 4)
        val photoAdapter = PhotoAdapter { photo ->
            photoClicked(photo)
        }

        if (mvm.isAlbumSelected.value == false) {
            setItemList(photoAdapter, vm.itemList)
        } else {
            vm.setAlbumItemList(mvm.albumTagName.value!!)
            setItemList(photoAdapter, vm.albumItemList)
        }

        with(layout_recycler_view) {
            adapter = photoAdapter
            layoutManager = recyclerManager
            setHasFixedSize(false)
            addItemDecoration(PhotoItemDecorator())
        }
    }

    private fun setItemList(
        photoAdapter: PhotoAdapter,
        itemList: LiveData<PagedList<PhotoInfo>>
    ) {
        itemList.observe(viewLifecycleOwner, Observer {
            photoAdapter.submitList(it)
        })
    }

    override fun photoClicked(photo: PhotoInfo?) {
        val intent = Intent(context, ViewerActivity::class.java)
        intent.putExtra(ViewerActivity.EXTRA_PHOTO_URI, photo?.uri)
        startActivity(intent)
    }

}