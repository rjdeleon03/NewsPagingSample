package com.pabsdl.newspaging.presentation.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.pabsdl.domain.usecases.GetBeersUseCase

class BeerListViewModel(private val getBeersUseCase: GetBeersUseCase): ViewModel() {

    val beerListData = getBeersUseCase().cachedIn(viewModelScope)
}