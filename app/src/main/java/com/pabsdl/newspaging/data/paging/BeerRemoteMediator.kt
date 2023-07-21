package com.pabsdl.newspaging.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.pabsdl.newspaging.data.database.BeerDatabase
import com.pabsdl.newspaging.data.database.BeerEntity
import com.pabsdl.newspaging.data.network.BeerService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDatabase: BeerDatabase,
    private val beerService: BeerService
): RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val beers = beerService.getNews(
                page = loadKey
            )
            beerDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDatabase.dao.clearAll()
                }
                val beerEntities = beers.map { it.toBeerEntity() }
                beerDatabase.dao.insertAll(beerEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }
}