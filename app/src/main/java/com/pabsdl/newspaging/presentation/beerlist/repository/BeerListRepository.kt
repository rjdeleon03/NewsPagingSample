package com.pabsdl.newspaging.presentation.beerlist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pabsdl.newspaging.data.model.BeerItem
import com.pabsdl.newspaging.data.network.BeerService
import com.pabsdl.newspaging.data.paging.BeerRemoteDataSource
import kotlinx.coroutines.flow.Flow

class BeerListRepository(private val service: BeerService) {

    fun getBeers(): Flow<PagingData<BeerItem>> {
        return Pager(PagingConfig(pageSize = BeerService.NETWORK_PAGE_SIZE)) {
            BeerRemoteDataSource(service)
        }.flow
    }
}

