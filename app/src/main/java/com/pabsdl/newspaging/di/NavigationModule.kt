package com.pabsdl.newspaging.di

import com.pabsdl.newspaging.presentation.old.navigation.BeerFlowCoordinator
import com.pabsdl.newspaging.presentation.old.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator()
    }

    @Provides
    fun provideBeerFlowCoordinator(navigator: Navigator): BeerFlowCoordinator {
        return BeerFlowCoordinator(navigator)
    }
}