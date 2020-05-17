package com.hackday.imageSearch.ui.album

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentAlbumBinding
import com.hackday.imageSearch.ui.album.adapter.AlbumItemDecorator
import com.hackday.imageSearch.ui.album.adapter.AlbumRecyclerAdapter
import com.hackday.imageSearch.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {
    override val vm: AlbumViewModel by viewModel()
    override fun getLayoutRes() = R.layout.fragment_album
    override fun setupBinding() {
        binding.vm = vm
    }

    lateinit var mvm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvm = activity?.run {
            ViewModelProvider(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid")
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
                    mvm.setAlbumTagName(position, label)
                    mvm.setFlag(true)
                }

            }
            layoutManager = recyclerManager
            setHasFixedSize(false)
            addItemDecoration(AlbumItemDecorator())
        }
    }

}