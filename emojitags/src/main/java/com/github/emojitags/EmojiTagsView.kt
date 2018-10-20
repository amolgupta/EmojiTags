package com.github.emojitags

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import java.io.IOException
import java.lang.AssertionError


open class EmojiTagsView constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : RecyclerView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, @Nullable attrs: AttributeSet?) : this(context, attrs, R.style.tags_view)


    var cancelable: Boolean = false
    set(value) {
        field = value
        if(adapter != null) {
            (adapter as TagsViewAdapter).cancelable = value
        }
    }

    private val itemPadding: Int = 8
    val tags = getTags(context)

    override fun setAdapter(adapter: Adapter<*>?) {
        if (adapter !is TagsViewAdapter) {
            throw AssertionError("Adapter does not extend TagsViewAdapter")
        }
        adapter.cancelable = cancelable
        adapter.meta = tags
        super.setAdapter(adapter)
    }

    init {
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        this.addItemDecoration(SpacesItemDecoration(itemPadding))
        this.layoutManager = layoutManager
        val attrs = context.obtainStyledAttributes(attrs, R.styleable.emoji_tags, R.attr.cancelable, defStyleAttr)
        try {
            cancelable = attrs.getBoolean(R.styleable.emoji_tags_cancelable, true)
        } finally {
            attrs.recycle()
        }
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


    companion object {

        fun getTags(context: Context): List<Tag> {
            val json = readEmojiFile(context)
            return Gson().fromJson(json, Array<Tag>::class.java).toList()
        }

        private fun readEmojiFile(context: Context): String {
            var text = ""
            try {
                val istream = context.resources.openRawResource(R.raw.emoji)
                val size = istream.available()
                val buffer = ByteArray(size)
                istream.read(buffer)
                istream.close()
                text = String(buffer)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return text
        }
    }
}

