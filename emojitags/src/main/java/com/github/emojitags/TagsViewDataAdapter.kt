package com.github.emojitags

import android.view.ViewGroup
import com.google.android.material.chip.Chip


class TagsViewDataAdapter(
    var tags: List<Emojiable>
) : TagsViewAdapter() {

    var listener: TagListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = Chip(parent.context)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.view as Chip).text = lookup(tags[position].alias()) +" "+ tags[position].displayName()
        holder.view.isCloseIconVisible = cancelable
        if (cancelable) {
            holder.view.setOnCloseIconClickListener {
                listener?.onTagDismiss(tags[position])
            }
        }
    }

    override fun getItemCount(): Int = tags.size

    interface TagListener {
        fun onTagDismiss(tag: Emojiable)
    }
}
