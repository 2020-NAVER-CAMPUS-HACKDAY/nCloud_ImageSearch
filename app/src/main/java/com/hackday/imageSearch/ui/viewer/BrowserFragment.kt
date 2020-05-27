package com.hackday.imageSearch.ui.viewer

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackday.imageSearch.R
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.ui.photo.PhotoViewModel
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import kotlinx.android.synthetic.main.activity_viewer.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrowserFragment : Fragment {

    private var curposition = 0
    private var imagePager: ViewPager? = null
    private var pagingImages: ImagesPagerAdapter? = null
    private lateinit var allImages: PagedList<PhotoInfo>
    private lateinit var dlg: DetailDialog
    private lateinit var animeContx: Context

    constructor()

    constructor(
        allImages: PagedList<PhotoInfo>,
        pos: Int,
        anim: Context
    ) {
        this.allImages = allImages
        curposition = pos
        animeContx = anim
    }

    fun newInstance(
        allImages: PagedList<PhotoInfo>,
        pos: Int,
        anim: Context
    ): BrowserFragment {
        return BrowserFragment(allImages, pos, anim)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_viewer, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dlg = DetailDialog(animeContx)
        initImagePager(view)
        initImagePagerChangeListener()
        btnClickListener()
    }

    private fun initImagePager(view: View) {
        imagePager = view.findViewById(R.id.viewPager)
        pagingImages = ImagesPagerAdapter(requireContext(), allImages)
        imagePager!!.adapter = pagingImages
        imagePager!!.offscreenPageLimit = 3
        imagePager!!.currentItem = curposition
    }

    private fun initImagePagerChangeListener() {
        imagePager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                curposition = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun btnClickListener() {
        btn_back.setOnClickListener {
            finishFrag()
        }

        btn_detail.setOnClickListener {
            dialogDetail()
        }
    }

    private fun finishFrag() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction().remove(this@BrowserFragment).commit()
        fragmentManager.popBackStack()
    }

    private fun dialogDetail() {
        if (allImages[curposition] != null) {
            dlg.start(allImages[curposition]!!)
        } else {
            Toast.makeText(animeContx, "사진 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}