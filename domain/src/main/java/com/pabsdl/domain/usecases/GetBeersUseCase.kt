package com.pabsdl.domain.usecases

import androidx.paging.PagingData
import com.pabsdl.domain.model.BeerItem
import com.pabsdl.domain.repository.GetBeersRepository
import kotlinx.coroutines.flow.Flow

class GetBeersUseCase(private val repository: GetBeersRepository) {

    operator fun invoke(): Flow<PagingData<BeerItem>> {
        return repository.getBeers()
    }
}