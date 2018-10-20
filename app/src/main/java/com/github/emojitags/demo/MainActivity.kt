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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_emoji_strings.adapter = TagsViewStringAdapter(
            arrayListOf(
                "blush",
                "laugh",
                "pray",
                "india",
                "football",
                "bowling",
                "hat",
                "100",
                "angry"
            ) as List<String>
        )
        view_emoji_strings.isCancelable = true
        (view_emoji_strings.adapter as TagsViewStringAdapter).listener = object : TagsViewStringAdapter.TagListener {
            override fun onTagDismiss(tag: String) {
                Toast.makeText(applicationContext, "Tag dismissed", Toast.LENGTH_SHORT).show()
            }
        }

        view_emoji_data.adapter = TagsViewDataAdapter(DummyTag.getDummyTags())
        (view_emoji_data.adapter as TagsViewDataAdapter).listener = object : TagsViewDataAdapter.TagListener {
            override fun onTagDismiss(tag: Emojiable) {
                Toast.makeText(
                    applicationContext,
                    "Tag dismissed is" + (tag as DummyTag).id,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
