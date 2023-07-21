package com.pabsdl.newspaging.data.network

import com.pabsdl.newspaging.data.model.NewsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    companion object {
        const val NETWORK_PAGE_SIZE = 60
    }

    @GET("beers")
    suspend fun getNews(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 60
    ): Response<List<NewsItem>>

}