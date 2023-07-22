package com.pabsdl.newspaging.presentation.beerlist.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.pabsdl.domain.model.BeerItem
import com.pabsdl.newspaging.presentation.ui.theme.ItemBackgroundColor

@Composable
fun BeerListContent(allBeers: LazyPagingItems<BeerItem>, modifier: Modifier) {
    LazyColumn(modifier) {
        items(
            count = allBeers.itemCount,
            key = {
                allBeers[it]?.id ?: 0
            }
        ) {
           allBeers[it]?.let { beerItem ->
               BeerListItem(beerItem)
           }
        }
    }
}

@Composable
fun BeerListItem(beerItem: BeerItem) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.ItemBackgroundColor
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = beerItem.name)
    }
}