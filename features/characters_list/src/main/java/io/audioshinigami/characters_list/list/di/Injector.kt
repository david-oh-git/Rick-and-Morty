package io.audioshinigami.characters_list.list.di

import dagger.hilt.android.EntryPointAccessors
import io.audioshinigami.characters_list.list.CharactersListFragment
import io.audioshinigami.core.di.CoreComponent
import io.audioshinigami.projectm.di.AppComponent

internal fun inject(fragment: CharactersListFragment) =
    DaggerCharacterListComponent
        .factory()
        .create(appComponent(fragment), coreComponent(fragment))
        .inject(fragment)

private fun appComponent(fragment: CharactersListFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )

private fun coreComponent(fragment: CharactersListFragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        CoreComponent::class.java
    )
