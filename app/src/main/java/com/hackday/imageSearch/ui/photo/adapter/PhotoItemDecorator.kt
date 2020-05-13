package com.hackday.imageSearch.ui.photo.adapter


import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PhotoItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val horizontalValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            2f,
            view.resources.displayMetrics
        ).toInt()

        val verticalValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            1f,
            view.resources.displayMetrics
        ).toInt()


        when (parent.getChildLayoutPosition(view) % 4) {
            3 -> outRect.set(0, 0, 0, verticalValue)
            else -> outRect.set(0, 0, horizontalValue, verticalValue)
        }

    }
}