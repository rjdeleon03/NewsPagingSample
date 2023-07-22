package com.pabsdl.domain.usecases

import com.pabsdl.domain.model.BeerItem
import com.pabsdl.domain.repository.GetBeersRepository
import kotlinx.coroutines.flow.Flow

class GetBeerDetailUseCase(private val repository: GetBeersRepository) {

    operator fun invoke(beerId: Int): Flow<BeerItem?> {
        return repository.getBeerDetail(beerId)
    }
}