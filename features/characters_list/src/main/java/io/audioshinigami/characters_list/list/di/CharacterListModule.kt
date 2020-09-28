package io.audioshinigami.characters_list.list.di

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


@DisableInstallInCheck
@Module
class CharacterListModule {

    @Provides
    @FeatureScope
    @CorotineScopeIo
    fun provideScopeIo(): CoroutineScope = CoroutineScope( Job() + Dispatchers.IO)

}