package com.pabsdl.newspaging.presentation

import androidx.lifecycle.ViewModel
import com.pabsdl.newspaging.presentation.navigation.BeerFlowCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val beerFlowCoordinator: BeerFlowCoordinator): ViewModel() {

    fun start() {
        beerFlowCoordinator.start()
    }
}