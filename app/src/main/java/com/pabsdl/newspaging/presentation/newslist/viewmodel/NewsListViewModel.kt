package com.pabsdl.newspaging.presentation.newslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pabsdl.newspaging.data.network.NewsService
import com.pabsdl.newspaging.presentation.newslist.repository.NewsListRepository

class NewsListViewModel(private val repository: NewsListRepository): ViewModel() {

    val newsListData = Pager(PagingConfig(pageSize = NewsService.NETWORK_PAGE_SIZE)) {
        repository.pagingSource
    }
    .flow
    .cachedIn(viewModelScope)
}