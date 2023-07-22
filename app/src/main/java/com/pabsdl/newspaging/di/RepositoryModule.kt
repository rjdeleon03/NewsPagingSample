package com.pabsdl.newspaging.di

import com.pabsdl.data.local.BeerDatabase
import com.pabsdl.data.remote.BeerApi
import com.pabsdl.data.repository.GetBeersRepositoryImpl
import com.pabsdl.domain.repository.GetBeersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideBeersRepository(beerDatabase: BeerDatabase, beerApi: BeerApi): GetBeersRepository {
        return GetBeersRepositoryImpl(beerDatabase, beerApi)
    }
}