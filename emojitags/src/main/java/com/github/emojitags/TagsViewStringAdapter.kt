package com.github.emojitags

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip


class TagsViewStringAdapter(
    var tags: List<String>
) : TagsViewAdapter() {

    var listener: TagsViewStringAdapter.TagListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = Chip(parent.context)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.view as Chip).text = "\uD83C\uDFAC" + tags[position]
        if (isCancelable) {
            holder.view.isCloseIconVisible = true
            holder.view.isCloseIconVisible = true
            holder.view.setOnCloseIconClickListener {
                listener?.onTagDismiss(tags[position])
            }
        }
    }

    override fun getItemCount(): Int = tags.size

    interface TagListener {
        fun onTagDismiss(tag: String)
    }
}
