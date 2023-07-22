package com.pabsdl.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    companion object {
        const val NETWORK_PAGE_SIZE = 60
    }

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 60
    ): Response<List<BeerDTO>>

}