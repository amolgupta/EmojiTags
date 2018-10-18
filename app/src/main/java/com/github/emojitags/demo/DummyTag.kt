package com.github.emojitags.demo

import com.github.emojitags.Emojiable


data class DummyTag(
    val name: String,
    val id: Int,
    val param_1: String,
    val param_2: String
) : Emojiable {
    override fun displayName(): String {
        return name
    }

    override fun alias(): List<String> {
        return arrayListOf(name,param_1, param_2)
    }
    companion object {

        fun getDummyTags():List<DummyTag>{

            return arrayListOf(
                DummyTag("apple",1,"fruit","food"),
                DummyTag("audio",2,"youtube","music"),
                DummyTag("Car",3,"road","Transport")
            )

        }

    }
}

