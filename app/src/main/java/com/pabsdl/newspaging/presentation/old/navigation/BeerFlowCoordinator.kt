package com.pabsdl.newspaging.presentation.old.navigation

class BeerFlowCoordinator(private val navigator: Navigator) {

    fun start() {
        navigator.showBeerList()
    }

    fun showBeerDetail(beerId: Int) {
        navigator.showBeerDetail(beerId)
    }
}