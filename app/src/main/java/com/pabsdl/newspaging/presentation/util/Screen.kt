package com.pabsdl.newspaging.presentation.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object BeerDetailScreen: Screen("beer_detail")
}