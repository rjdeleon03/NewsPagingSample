package com.pabsdl.newspaging.presentation.beerlist.repository

import com.pabsdl.newspaging.data.database.BeerDatabase
import com.pabsdl.newspaging.data.network.BeerService
import com.pabsdl.newspaging.data.paging.BeerRemoteDataSource

class BeerListRepository(val beerDatabase: BeerDatabase,
                         val beerService: BeerService) {
}