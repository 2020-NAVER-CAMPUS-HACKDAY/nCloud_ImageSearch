package com.hackday.imageSearch.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseActivity
import com.hackday.imageSearch.databinding.ActivityMainBinding
import com.hackday.imageSearch.ui.main.adapter.MainViewPagerAdapter
import com.hackday.imageSearch.ui.photo.PhotoFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val vm: MainViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_main

    private lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setViewPager()
        observe()
        initSearchView()
    }

    private fun setViewPager() {
        viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        layout_viewPager.adapter = viewPagerAdapter

        layout_tab_layout.apply {
            setupWithViewPager(layout_viewPager)
        }
    }

    private fun observe() {
        vm.replaceFragment.observe(this, Observer {
            if (it) {
                //toast("눌렀당!" + vm.albumTagName.value)
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.layout_container, PhotoFragment())
                    commit()
                }
            }
        })

        vm.back.observe(this, Observer {
            if (it) {
                vm.setReplaceFragment(false)
                vm.setIsBtnSearchClicked(false)
            }
        })
    }

    private fun initSearchView() {
        btn_search.setOnClickListener {
            val edtText = edit_search.text.toString()
            if (edtText != "") {
                vm.setReplaceFragment(true)
                vm.setIsBtnSearchClicked(true)
                vm.setSearchTagName(edtText)
            } else {
                toast("검색어를 입력하세요.")
            }
        }
    }

    override fun onBackPressed() {  // 뒤로가기 눌렀을 때 바로 앱 종료되는 것 막기
        if (vm.replaceFragment.value!!) {
            vm.setReplaceFragment(false)
            vm.setIsBtnSearchClicked(false)
        } else {
            super.onBackPressed()
        }
    }
}
