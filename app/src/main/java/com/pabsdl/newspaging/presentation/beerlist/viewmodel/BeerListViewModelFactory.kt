package com.pabsdl.newspaging.presentation.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pabsdl.domain.usecases.GetBeersUseCase

class BeerListViewModelFactory(private val getBeersUseCase: GetBeersUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerListViewModel(getBeersUseCase) as T
    }
}