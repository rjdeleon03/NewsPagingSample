package com.pabsdl.newspaging.presentation.newslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabsdl.newspaging.data.NewsItemResponse
import com.pabsdl.newspaging.data.network.NetworkResult
import com.pabsdl.newspaging.presentation.newslist.repository.NewsListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsListViewModel(private val repository: NewsListRepository): ViewModel() {

    val newsLiveData = MutableLiveData<NetworkResult<NewsItemResponse>>()

    fun getNews() {
        newsLiveData.postValue(NetworkResult.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getNews()
            newsLiveData.postValue(result)
        }
    }
}