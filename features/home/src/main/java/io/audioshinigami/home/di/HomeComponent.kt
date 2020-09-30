package io.audioshinigami.home.di

import dagger.Component
import io.audioshinigami.home.HomeFragment
import io.audioshinigami.projectm.di.AppComponent

@HomeFeatureScope
@Component(
    dependencies = [AppComponent::class]
)
interface HomeComponent {

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): HomeComponent
    }

    fun inject(target: HomeFragment)
}
