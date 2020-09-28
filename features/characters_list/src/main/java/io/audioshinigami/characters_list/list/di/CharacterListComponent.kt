package io.audioshinigami.characters_list.list.di

import dagger.Component
import io.audioshinigami.characters_list.list.CharactersListFragment
import io.audioshinigami.core.di.CoreComponent
import io.audioshinigami.projectm.di.AppComponent


@FeatureScope
@Component(
    dependencies = [AppComponent::class, CoreComponent::class],
    modules = [CharacterListModule::class]
)
interface CharacterListComponent {

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent,
            component: CoreComponent
        ): CharacterListComponent
    }

    fun inject(target: CharactersListFragment)
}