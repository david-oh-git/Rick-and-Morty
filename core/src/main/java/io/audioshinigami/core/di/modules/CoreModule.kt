package io.audioshinigami.core.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.audioshinigami.core.network.ApiFactory
import io.audioshinigami.core.network.services.RickAndMortyService
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RickAndMortyService = ApiFactory.provideRickAndMortyService()
}