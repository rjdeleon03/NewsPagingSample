package com.pabsdl.newspaging.presentation.newslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pabsdl.newspaging.presentation.newslist.repository.NewsListRepository

class NewsListViewModelFactory(private val repository: NewsListRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsListViewModel(repository) as T
    }
}