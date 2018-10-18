package com.github.emojitags

interface Emojiable {
    fun displayName(): String
    fun alias(): List<String>
}