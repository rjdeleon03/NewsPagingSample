package com.pabsdl.newspaging.presentation.beerlist.view

import com.pabsdl.domain.model.BeerItem

sealed class BeerListViewItem() {
    class Item(val index: Int, val data: BeerItem): BeerListViewItem()
    class Separator(val label: String) : BeerListViewItem()
}