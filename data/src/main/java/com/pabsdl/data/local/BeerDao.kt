package com.pabsdl.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pabsdl.domain.model.BeerItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<BeerEntity>)

    @Query("DELETE FROM beers")
    suspend fun clearAll()

    @Query("SELECT * FROM beers")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    // For getting an individual beer item
    @Query("SELECT * FROM beers WHERE id=:beerId")
    fun getBeer(beerId: Int): Flow<BeerItem?>
}