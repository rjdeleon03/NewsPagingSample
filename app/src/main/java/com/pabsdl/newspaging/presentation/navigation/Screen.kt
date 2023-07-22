package com.pabsdl.newspaging.presentation.navigation

sealed class Screen(val route: String) {
    object BeerList : Screen("beer_list")
    object BeerDetail : Screen("beer_detail/{beerId}") {
        fun passMovieId(beerId: String) = "beer_detail/$beerId"
    }
}
