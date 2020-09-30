package io.audioshinigami.home.di

import dagger.hilt.android.EntryPointAccessors
import io.audioshinigami.home.HomeFragment
import io.audioshinigami.projectm.di.AppComponent

internal fun inject(target: HomeFragment) =
    DaggerHomeComponent
        .factory()
        .create(appComponent(target))
        .inject(target)

private fun appComponent(fragment: HomeFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )
