package com.hackday.imageSearch.ui.main

import android.os.Bundle
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseActivity
import com.hackday.imageSearch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val vm: MainViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_main

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
