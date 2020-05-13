package com.hackday.imageSearch.ui.album.adapter

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AlbumItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val horizontalValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            15f,
            view.resources.displayMetrics
        ).toInt()


        when (parent.getChildLayoutPosition(view)) {
            0 -> outRect.set(horizontalValue, 0, horizontalValue, 0)
            state.itemCount - 1 -> outRect.set(0, 0, horizontalValue, 0)
            else -> outRect.set(0, 0, horizontalValue, 0)
        }

    }
}