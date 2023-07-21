package com.pabsdl.newspaging.data.network

import com.pabsdl.newspaging.BuildConfig
import com.pabsdl.newspaging.data.NewsItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("country") country: String = "us"
    ): Response<NewsItemResponse>

}