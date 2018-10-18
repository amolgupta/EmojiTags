package com.github.emojitags.demo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.emojitags.EmojiTagsView
import com.github.emojitags.TagsViewStringAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private val TAG: String? = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stringTagsView = EmojiTagsView(this)
        stringTagsView.adapter = TagsViewStringAdapter(arrayListOf("asdfadf", "ddddd", "efe","afadf", "ddddd", "defefefefe","asdfadf", "ddddd", "defefefefe"))
        stringTagsView.isCancelable = true
        (stringTagsView.adapter as TagsViewStringAdapter).listener = object : TagsViewStringAdapter.TagListener {
            override fun onTagDismiss(tag: String) {
                Toast.makeText(applicationContext,"Tag dismissed",Toast.LENGTH_SHORT).show()
                Log.d(TAG,"Tag dismissed")
            }
        }
        container.addView(stringTagsView)



    }
}
