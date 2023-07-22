package com.pabsdl.newspaging.presentation.beerlist.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.pabsdl.newspaging.presentation.beerlist.viewmodel.BeerListViewModel
import com.pabsdl.newspaging.presentation.ui.theme.AppContentColor
import com.pabsdl.newspaging.presentation.ui.theme.AppThemeColor

@Composable
fun BeerListScreen(viewModel: BeerListViewModel = hiltViewModel()) {

    val allBeers = viewModel.beerListData.collectAsLazyPagingItems()

    Scaffold(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        contentColor = MaterialTheme.colors.AppContentColor,
        topBar = {
//            HomeTopBar()
        },
        content = { padding ->
            BeerListContent(allBeers = allBeers, modifier = Modifier.padding(padding))
        }
    )
}