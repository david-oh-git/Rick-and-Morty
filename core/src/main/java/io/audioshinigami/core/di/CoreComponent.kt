package io.audioshinigami.core.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.audioshinigami.core.network.services.RickAndMortyService

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreComponent {

    val service: RickAndMortyService

}