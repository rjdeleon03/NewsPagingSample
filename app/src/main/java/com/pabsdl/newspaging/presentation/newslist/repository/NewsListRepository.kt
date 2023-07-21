package com.pabsdl.newspaging.presentation.newslist.repository

import com.pabsdl.newspaging.data.network.NewsService
import com.pabsdl.newspaging.data.paging.NewsRemoteDataSource

class NewsListRepository(private val service: NewsService) {

    val pagingSource = NewsRemoteDataSource(service)
}