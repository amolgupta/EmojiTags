package com.github.emojitags

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


open class EmojiTagsView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.tags_view
) : RecyclerView(context, attrs, defStyleAttr) {


    var isCancelable: Boolean = false
    set(value){
        field = value
        (adapter as TagsViewAdapter).isCancelable = field
    }
    private val itemPadding: Int = 8

    init {

        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        this.addItemDecoration(SpacesItemDecoration(itemPadding))
        this.layoutManager = layoutManager
    }

    inner class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
            outRect.top = space
        }
    }
}

