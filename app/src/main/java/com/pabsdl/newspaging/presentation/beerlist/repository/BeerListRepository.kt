package com.pabsdl.newspaging.presentation.beerlist.repository

import com.pabsdl.newspaging.data.network.BeerService
import com.pabsdl.newspaging.data.paging.BeerRemoteDataSource

class BeerListRepository(private val service: BeerService) {

    val pagingSource = BeerRemoteDataSource(service)
}