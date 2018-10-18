package com.github.emojitags


data class Tag(
    val emoji: String,
    val description: String,
    val category: String,
    val aliases: List<String>,
    val tags: List<String>,
    val unicode_version: String,
    val ios_version: String


)

data class Tags(val tags: List<Tag>)