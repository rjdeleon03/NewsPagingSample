package com.pabsdl.newspaging.data.model

data class NewsItemResponse(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: ArrayList<NewsItem> = arrayListOf()
)