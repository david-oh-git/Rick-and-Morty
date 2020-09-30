package io.audioshinigami.core.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import io.audioshinigami.core.factory.ViewModelFactory

@[Module DisableInstallInCheck]
interface FactoryModules {

    @get:Binds
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory
}