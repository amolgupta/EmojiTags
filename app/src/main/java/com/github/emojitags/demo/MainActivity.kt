package com.github.emojitags.demo

import android.app.Activity
import android.os.Bundle
import com.github.emojitags.EmojiTagsView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tag = EmojiTagsView(this)
        tag.items = arrayListOf("asdfadf", "ddddd", "efe","afadf", "ddddd", "defefefefe","asdfadf", "ddddd", "defefefefe")
        tag.isCancelable = true
        container.addView(tag)
    }
}
