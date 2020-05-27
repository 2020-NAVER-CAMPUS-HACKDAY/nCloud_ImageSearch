package com.hackday.imageSearch.ui.viewer

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.paging.PagedList
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackday.imageSearch.R
import com.hackday.imageSearch.model.PhotoInfo

class ImagesPagerAdapter(val context: Context, private val allImages: PagedList<PhotoInfo>) : PagerAdapter() {
    private lateinit var image: ImageView

    override fun getCount(): Int {
        return allImages.size
    }

    @SuppressLint("InflateParams")
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

        Glide.with(context)
            .load(pic.uri)
            .apply(RequestOptions().fitCenter())
            .into(image)

        (containerCollection as ViewPager).addView(view)
        return view
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