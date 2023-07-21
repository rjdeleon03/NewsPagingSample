package com.pabsdl.newspaging.presentation.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pabsdl.newspaging.presentation.beerlist.repository.BeerListRepository

class BeerListViewModelFactory(private val repository: BeerListRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerListViewModel(repository) as T
    }
}