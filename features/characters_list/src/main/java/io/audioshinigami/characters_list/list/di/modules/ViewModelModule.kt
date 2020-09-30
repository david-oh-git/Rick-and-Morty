package io.audioshinigami.characters_list.list.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import io.audioshinigami.characters_list.list.CharactersListViewModel
import io.audioshinigami.core.di.ViewModelKey

@DisableInstallInCheck
@Module
interface ViewModelModule {

    @get:[Binds IntoMap ViewModelKey(CharactersListViewModel::class)]
    val CharactersListViewModel.viewModel: ViewModel
}