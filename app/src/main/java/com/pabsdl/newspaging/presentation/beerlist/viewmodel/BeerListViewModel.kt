package com.pabsdl.newspaging.presentation.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pabsdl.newspaging.data.network.BeerService
import com.pabsdl.newspaging.presentation.beerlist.repository.BeerListRepository

class BeerListViewModel(private val repository: BeerListRepository): ViewModel() {

    val beerListData = repository.getBeers()
        .cachedIn(viewModelScope)
}