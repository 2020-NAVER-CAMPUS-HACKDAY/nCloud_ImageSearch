package com.hackday.imageSearch.ui.main

import android.os.Bundle
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseFragment
import com.hackday.imageSearch.databinding.FragmentPhotoBinding
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

}