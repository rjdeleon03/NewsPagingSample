package com.pabsdl.data.local

import android.content.Context
import androidx.room.Room

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