package com.github.emojitags

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import java.io.IOException
import java.lang.AssertionError


open class EmojiTagsView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.tags_view
) : RecyclerView(context, attrs, defStyleAttr) {


    var isCancelable: Boolean = false
        set(value) {
            field = value
            (adapter as TagsViewAdapter).isCancelable = field
        }
    private val itemPadding: Int = 8
    val tags = getTags(context)

    override fun setAdapter(adapter: Adapter<*>?) {
        if (adapter !is TagsViewAdapter) {
            throw AssertionError("Adapter does not extend TagsViewAdapter")
        }
        super.setAdapter(adapter)
        adapter.meta = tags
    }

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

