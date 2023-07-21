package com.pabsdl.newspaging.presentation.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.pabsdl.newspaging.data.network.BeerService
import com.pabsdl.newspaging.data.paging.BeerRemoteMediator
import com.pabsdl.newspaging.presentation.beerlist.repository.BeerListRepository
import kotlinx.coroutines.flow.map

class BeerListViewModel(private val repository: BeerListRepository): ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val beerListData = Pager(
        config = PagingConfig(pageSize = BeerService.NETWORK_PAGE_SIZE),
        remoteMediator = BeerRemoteMediator(repository.beerDatabase, repository.beerService),
        pagingSourceFactory = { repository.beerDatabase.dao.pagingSource() }
    )
        .flow
        .map { pagingData ->
            pagingData.map { it.toBeerItem() }
        }
        .cachedIn(viewModelScope)
}