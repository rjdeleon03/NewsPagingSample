package com.pabsdl.newspaging.presentation.screens.detail

import androidx.lifecycle.ViewModel
import com.pabsdl.domain.model.BeerItem
import com.pabsdl.domain.usecases.GetBeerDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBeerDetailUseCase: GetBeerDetailUseCase,
): ViewModel() {

    fun getBeerDetail(id: Int): Flow<BeerItem?> {
        return getBeerDetailUseCase(id)
    }
}