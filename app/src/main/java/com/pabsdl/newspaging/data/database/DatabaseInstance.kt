package com.pabsdl.newspaging.data.database

import android.content.Context
import androidx.room.Room
import com.pabsdl.newspaging.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object DatabaseInstance {

    var beerDatabase: BeerDatabase? = null

    fun getDatabaseInstance(context: Context): BeerDatabase {
        if (beerDatabase == null) {
            beerDatabase = Room.databaseBuilder(
                context,
                BeerDatabase::class.java,
                "beers.db"
            ).build()
        }
        return beerDatabase!!
    }

}