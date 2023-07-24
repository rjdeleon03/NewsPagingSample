package com.pabsdl.newspaging.presentation.beerlist.view

import com.pabsdl.domain.model.BeerItem

sealed class BeerViewItem {
    class BeerPagingActualItem(data: BeerItem)
    class BeerPagingSeparator()
}
