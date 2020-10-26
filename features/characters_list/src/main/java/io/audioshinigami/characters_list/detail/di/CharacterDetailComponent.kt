/*
 * MIT License
 *
 * Copyright (c) 22/10/2020 7:28   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.audioshinigami.characters_list.detail.di

import dagger.Component
import io.audioshinigami.characters_list.detail.CharacterDetailFragment
import io.audioshinigami.characters_list.detail.di.modules.CharacterDetailModule
import io.audioshinigami.characters_list.detail.di.modules.ViewModelModule
import io.audioshinigami.core.di.CoreComponent
import io.audioshinigami.core.di.modules.FactoryModules
import io.audioshinigami.projectm.di.AppComponent

/**
 * CharacterDetail daggerHilt component.
 */
@CharacterDetailScope
@Component(
    dependencies = [AppComponent::class, CoreComponent::class] ,
    modules = [CharacterDetailModule::class, ViewModelModule::class, FactoryModules::class]
)
interface CharacterDetailComponent {

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent,
            component: CoreComponent
        ): CharacterDetailComponent
    }

    fun inject(target: CharacterDetailFragment)
}