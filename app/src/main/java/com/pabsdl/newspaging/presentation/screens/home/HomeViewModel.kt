package com.pabsdl.newspaging.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pabsdl.domain.usecases.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getBeersUseCase: GetBeersUseCase
): ViewModel() {

    val beerListData = getBeersUseCase()
        .cachedIn(viewModelScope)
}