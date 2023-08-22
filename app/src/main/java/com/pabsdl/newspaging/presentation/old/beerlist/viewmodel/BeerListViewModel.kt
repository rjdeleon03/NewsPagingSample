package com.pabsdl.newspaging.presentation.old.beerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.pabsdl.domain.usecases.GetBeersUseCase
import com.pabsdl.newspaging.presentation.old.beerlist.view.BeerListViewItem
import com.pabsdl.newspaging.presentation.old.navigation.BeerFlowCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    getBeersUseCase: GetBeersUseCase,
    private val beerFlowCoordinator: BeerFlowCoordinator
): ViewModel() {

    val beerListData = getBeersUseCase()
        .map { pagingData ->
            var index = 0
            pagingData
                .map { BeerListViewItem.Item(index++, it) }
                .insertSeparators { before, _ ->
                    if (before?.index?.plus(1)?.mod(5) == 0) {
                        BeerListViewItem.Separator("--- Group ${before.index} ---")
                    } else {
                        null
                    }
                }
        }
        .cachedIn(viewModelScope)

    fun showBeerDetail(beerId: Int) {
        beerFlowCoordinator.showBeerDetail(beerId)
    }
}