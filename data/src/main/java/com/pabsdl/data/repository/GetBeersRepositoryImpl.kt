package com.pabsdl.data.repository

import androidx.paging.*
import com.pabsdl.data.local.BeerDatabase
import com.pabsdl.data.mappers.Mappers.toDomain
import com.pabsdl.data.paging.BeerRemoteMediator
import com.pabsdl.data.remote.BeerApi
import com.pabsdl.domain.model.BeerItem
import com.pabsdl.domain.repository.GetBeersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBeersRepositoryImpl(private val beerDatabase: BeerDatabase, private val beerApi: BeerApi): GetBeersRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getBeers(): Flow<PagingData<BeerItem>> {
        return Pager(
            config = PagingConfig(pageSize = BeerApi.NETWORK_PAGE_SIZE),
            remoteMediator = BeerRemoteMediator(
                beerDatabase,
                beerApi
            ),
            pagingSourceFactory = { beerDatabase.dao.pagingSource() }
        )
        .flow
        .map { pagingData -> pagingData.map { it.toDomain() } }
    }

    override fun getBeerDetail(beerId: Int): Flow<BeerItem?> {
        return beerDatabase.dao.getBeer(beerId)
    }
}