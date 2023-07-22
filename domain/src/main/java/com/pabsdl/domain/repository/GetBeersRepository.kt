package com.pabsdl.domain.repository

import androidx.paging.PagingData
import com.pabsdl.domain.model.BeerItem
import kotlinx.coroutines.flow.Flow

interface GetBeersRepository {

    fun getBeers(): Flow<PagingData<BeerItem>>

    fun getBeerDetail(beerId: Int): Flow<BeerItem?>
}