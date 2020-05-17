package com.hackday.imageSearch.ui.album

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentAlbumBinding
import com.hackday.imageSearch.ui.album.adapter.AlbumItemDecorator
import com.hackday.imageSearch.ui.album.adapter.AlbumRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {
    override val vm: AlbumViewModel by viewModel()
    override fun getLayoutRes() = R.layout.fragment_album
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

        val recyclerManager = LinearLayoutManager(context!!, RecyclerView.HORIZONTAL, false)

        with(layout_recycler_view) {
            adapter = AlbumRecyclerAdapter().apply {
                setOnItemClickListener { position, label ->
                    toast(position.toString() + label)
                }

            }
            layoutManager = recyclerManager
            setHasFixedSize(false)
            addItemDecoration(AlbumItemDecorator())
        }
    }

}