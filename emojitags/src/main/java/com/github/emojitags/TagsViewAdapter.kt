package com.github.emojitags

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TagsViewAdapter: RecyclerView.Adapter<TagsViewAdapter.ViewHolder>(){
    var isCancelable: Boolean = false
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}