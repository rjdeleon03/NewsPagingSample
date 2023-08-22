package com.pabsdl.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.pabsdl.data.local.BeerDatabase
import com.pabsdl.data.local.BeerEntity
import com.pabsdl.data.mappers.Mappers.toLocal
import com.pabsdl.data.remote.BeerApi
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.ceil

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDatabase: BeerDatabase,
    private val beerApi: BeerApi
): RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                // KEY -- Page number
                // Key when user refreshes is set to 1
                LoadType.REFRESH -> 1
                // Prepend key is always end of page
                LoadType.PREPEND -> {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                // Append key is always the last page + 1
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        ceil(lastItem.id / (state.config.pageSize * 1.0)).toInt() + 1
                    }
                }
            }
            // Setup API
            val beers = beerApi.getBeers(
                page = loadKey
            )
            // Insert in DB when API results are obtained
            beerDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDatabase.dao.clearAll()
                }
                val beerEntities = beers.body()?.map { it.toLocal() } ?: emptyList()
                beerDatabase.dao.insertAll(beerEntities)
            }
            // Return success
            MediatorResult.Success(
                endOfPaginationReached = beers.body()?.isEmpty() == true
            )
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }
}