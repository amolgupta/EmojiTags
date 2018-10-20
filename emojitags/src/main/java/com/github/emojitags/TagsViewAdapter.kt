package com.github.emojitags

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TagsViewAdapter : RecyclerView.Adapter<TagsViewAdapter.ViewHolder>() {
    var cancelable: Boolean = false
    lateinit var meta: List<Tag>

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun lookup(input: List<String>): String {
        val results = meta.filter { it ->
            input.map { it.toLowerCase() }
                .intersect(it.tags).isNotEmpty() ||
                    input.map { it.toLowerCase() }
                        .intersect(it.aliases).isNotEmpty()
        }
        return if (results.isNotEmpty()) results.first().emoji else ""
    }

    fun lookup(input: String): String {
        val results = meta.filter { it -> it.tags.contains(input.toLowerCase()) || it.aliases.contains(input.toLowerCase()) }
        return if (results.isNotEmpty()) results.first().emoji else ""
    }

}