package com.pabsdl.newspaging.presentation.old.beerdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabsdl.domain.model.BeerItem
import com.pabsdl.domain.usecases.GetBeerDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    private val getBeerDetailUseCase: GetBeerDetailUseCase
): ViewModel() {

    private val _selectedBeer: MutableStateFlow<BeerItem?> = MutableStateFlow(null)
    val selectedBeer: StateFlow<BeerItem?>
        get() = _selectedBeer

    fun getBeerDetail(beerId: Int) {
        viewModelScope.launch {
            getBeerDetailUseCase(beerId).collect {
                _selectedBeer.value = it
            }
        }
    }

}