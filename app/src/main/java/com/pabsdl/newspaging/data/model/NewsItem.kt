package com.pabsdl.newspaging.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsItem(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) {
}

@JsonClass(generateAdapter = true)
data class Source(
    val id: String,
    val name: String
)