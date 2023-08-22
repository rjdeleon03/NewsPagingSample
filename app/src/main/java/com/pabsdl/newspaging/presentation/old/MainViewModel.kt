package com.pabsdl.newspaging.presentation.old

import androidx.lifecycle.ViewModel
import com.pabsdl.newspaging.presentation.old.navigation.BeerFlowCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val beerFlowCoordinator: BeerFlowCoordinator): ViewModel() {

    init {
        beerFlowCoordinator.start()
    }
}