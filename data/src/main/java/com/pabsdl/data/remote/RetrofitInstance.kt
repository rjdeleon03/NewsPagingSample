package com.pabsdl.data.remote

import com.pabsdl.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    var beerApi: BeerApi? = null

    fun getRetrofitInstance(): BeerApi {
        if (beerApi == null) {

            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
                connectTimeout(20, TimeUnit.SECONDS)
            }.build()

            beerApi = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
        return beerApi!!
    }

}