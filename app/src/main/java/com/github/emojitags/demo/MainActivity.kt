package com.github.emojitags.demo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.emojitags.EmojiTagsView
import com.github.emojitags.Emojiable
import com.github.emojitags.TagsViewDataAdapter
import com.github.emojitags.TagsViewStringAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private val TAG: String? = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stringTagsView = EmojiTagsView(this)
        stringTagsView.adapter = TagsViewStringAdapter(arrayListOf("blush", "laugh", "pray","india", "football", "bowling","hat", "100", "angry"))
        stringTagsView.isCancelable = true
        (stringTagsView.adapter as TagsViewStringAdapter).listener = object : TagsViewStringAdapter.TagListener {
            override fun onTagDismiss(tag: String) {
                Toast.makeText(applicationContext,"Tag dismissed",Toast.LENGTH_SHORT).show()
            }
        }
        container.addView(stringTagsView)

        val objectTagsView = EmojiTagsView(this)
        objectTagsView.adapter = TagsViewDataAdapter(DummyTag.getDummyTags())
        (objectTagsView.adapter as TagsViewDataAdapter).listener = object : TagsViewDataAdapter.TagListener {
            override fun onTagDismiss(tag: Emojiable) {
                Toast.makeText(
                    applicationContext,
                    "Tag dismissed is" + (tag as DummyTag).id,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        container.addView(objectTagsView)


    }
}
