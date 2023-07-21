package com.pabsdl.newspaging.presentation.newslist.repository

import com.pabsdl.newspaging.data.model.NewsItemResponse
import com.pabsdl.newspaging.data.network.NetworkResult
import com.pabsdl.newspaging.data.network.NewsService
import com.pabsdl.newspaging.data.paging.NewsRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class NewsListRepository(private val service: NewsService) {

    val pagingSource = NewsRemoteDataSource(service)

    suspend fun getNews(): NetworkResult<NewsItemResponse> {
        try {
            val response = service.getNews()
            if (response.isSuccessful) {
                response.body()?.let {
                    return NetworkResult.Success(it)
                }
            }
            return NetworkResult.Error(response.message())
        } catch (ex: IOException) {
            return NetworkResult.Error(ex.message)
        } catch (ex: HttpException) {
            return NetworkResult.Error(ex.message)
        }
    }
}