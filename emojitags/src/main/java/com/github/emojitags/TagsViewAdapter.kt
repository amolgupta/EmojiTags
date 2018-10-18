package com.github.emojitags

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip

abstract class TagsViewAdapter : RecyclerView.Adapter<TagsViewAdapter.ViewHolder>() {
    var isCancelable: Boolean = false
    lateinit var meta: List<Tag>

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun lookup(input: List<String>): String {
        return meta.first { it -> input.intersect(it.tags).isNotEmpty() || input.intersect(it.aliases).isNotEmpty() }.emoji
    }

    fun lookup(input: String): String {
        val results = meta.filter { it -> it.tags.contains(input) || it.aliases.contains(input)}
        return if (results.isNotEmpty()) results.first().emoji else ""
    }

}