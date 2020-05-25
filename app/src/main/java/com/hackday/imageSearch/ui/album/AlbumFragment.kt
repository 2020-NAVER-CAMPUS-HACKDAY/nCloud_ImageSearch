package com.hackday.imageSearch.ui.album

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentAlbumBinding
import com.hackday.imageSearch.ui.album.adapter.AlbumAdapter
import com.hackday.imageSearch.ui.album.adapter.AlbumItemDecorator
import com.hackday.imageSearch.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {
    override val vm: AlbumViewModel by viewModel()
    override fun getLayoutRes() = R.layout.fragment_album
    override fun setupBinding() {
        binding.vm = vm
    }

    private val mvm by activityViewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {

        val recyclerManager = GridLayoutManager(context, 2)
        val albumAdapter = AlbumAdapter()

        vm.albumTitleList.observe(viewLifecycleOwner, Observer {
            albumAdapter.submitList(it)
        })

        with(layout_recycler_view) {
            adapter = albumAdapter.apply {
                setOnItemClickListener { label ->
                    mvm.setAlbumTagTitle(label)
                    mvm.setReplaceFragment(true)
                }

            }
            layoutManager = recyclerManager
            setHasFixedSize(false)
            addItemDecoration(AlbumItemDecorator())
        }
    }

}