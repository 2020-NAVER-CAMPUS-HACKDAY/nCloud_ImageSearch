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
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import kotlinx.android.synthetic.main.activity_viewer.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrowserFragment : Fragment {

    val pvm: PhotoInfoViewModel by viewModel()
    private var position = 0
    private var animeContx: Context? = null
    private var imagePager: ViewPager? = null
    private var pagingImages: ImagesPagerAdapter? = null
    private var previousSelected = -1
    private lateinit var allImages: PagedList<PhotoInfo>
    private lateinit var dlg: DetailDialog

    constructor()

    constructor(
        allImages: PagedList<PhotoInfo>,
        pos: Int,
        anim: Context?
    ) {
        this.allImages = allImages
        position = pos
        animeContx = anim
    }

    fun newInstance(
        allImages: PagedList<PhotoInfo>,
        pos: Int,
        anim: Context?
    ): BrowserFragment? {
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

        dlg = DetailDialog(animeContx!!)

        imagePager = view.findViewById(R.id.viewPager)
        pagingImages = ImagesPagerAdapter()
        imagePager!!.adapter = pagingImages
        imagePager!!.offscreenPageLimit = 3
        imagePager!!.currentItem =
            position //displaying the image at the current position passed by the ImageDisplay Activity

        /**
         * this listener controls the visibility of the recyclerView
         * indication and it current position in respect to the image ViewPager
         */
        previousSelected = position
        imagePager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (previousSelected != -1) {
                    previousSelected = position
                    // photoInfo
                } else {
                    previousSelected = position
                    // photoInfo
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    inner class ImagesPagerAdapter() : PagerAdapter() {
        private lateinit var image: ImageView

        override fun getCount(): Int {
            return allImages.size
        }

        override fun instantiateItem(
            containerCollection: ViewGroup,
            position: Int
        ): Any {
            val layoutinflater = containerCollection.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = layoutinflater.inflate(R.layout.browser_viewer_pager, null)
            image = view.findViewById(R.id.pager_image)
            ViewCompat.setTransitionName(image, position.toString() + "picture")
            val pic: PhotoInfo = allImages[position]!!
            dlg.start(pic)

            Glide.with(animeContx!!)
                .load(pic.uri)
                .apply(RequestOptions().fitCenter())
                .into(image)

            btn_back.setOnClickListener {
                finishFrag()
            }

            btn_detail.setOnClickListener {
                dialogDetail(pic)
            }

            (containerCollection as ViewPager).addView(view)
            return view
        }

        fun finishFrag() {
            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().remove(this@BrowserFragment).commit()
            fragmentManager.popBackStack()
        }

        fun dialogDetail(pic: PhotoInfo) {
            dlg.show()
        }

        override fun destroyItem(
            containerCollection: ViewGroup,
            position: Int,
            view: Any
        ) {
            (containerCollection as ViewPager).removeView(view as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as View
        }
    }
}