package com.hackday.imageSearch.ui.photo

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.Fade
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentPhotoBinding
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.ui.main.MainViewModel
import com.hackday.imageSearch.ui.photo.adapter.PhotoAdapter
import com.hackday.imageSearch.ui.photo.adapter.PhotoItemDecorator
import com.hackday.imageSearch.ui.viewer.BrowserFragment
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
        val photoAdapter = PhotoAdapter { img, pos, clist ->
            photoClicked(img, pos, clist)
        }

        when (mvm.replaceFragment.value) {
            false -> setItemList(photoAdapter, vm.itemList)
            else -> {
                if (mvm.isBtnSearchClicked.value == true) {
                    vm.setAlbumItemList(mvm.searchTagName.value!!.toLowerCase().capitalize())
                } else {
                    vm.setAlbumItemList(mvm.albumTagTitle.value!!)
                }
                setItemList(photoAdapter, vm.newItemList)
            }
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

    override fun photoClicked(holer_image: ImageView, position: Int, clist: PagedList<PhotoInfo>?) {
        val browser = BrowserFragment().newInstance(clist!!, position, context)

        closeKeyboard()

        browser!!.enterTransition = Fade()
        browser.exitTransition = Fade()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .addSharedElement(holer_image, "transitionPhotofrag")
            .add(R.id.layout_top, browser!!)
            .addToBackStack(null)
            .commit()
    }

    fun closeKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.getWindowToken(), 0)
    }

}