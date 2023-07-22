package com.pabsdl.newspaging.di

import com.pabsdl.domain.repository.GetBeersRepository
import com.pabsdl.domain.usecases.GetBeerDetailUseCase
import com.pabsdl.domain.usecases.GetBeersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetBeersUseCase(repository: GetBeersRepository): GetBeersUseCase {
        return GetBeersUseCase(repository)
    }

    @Provides
    fun provideGetBeerDetailUseCase(repository: GetBeersRepository): GetBeerDetailUseCase {
        return GetBeerDetailUseCase(repository)
    }
}