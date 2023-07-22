package com.pabsdl.newspaging.presentation.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.pabsdl.domain.usecases.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(getBeersUseCase: GetBeersUseCase): ViewModel() {

    val beerListData = getBeersUseCase().cachedIn(viewModelScope)
}