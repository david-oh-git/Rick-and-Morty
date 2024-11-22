/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2020 $today.hour24:$today.minute   David Osemwota.
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
package io.audioshinigami.core.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.audioshinigami.core.data.source.CharacterFavouriteDataSource
import io.audioshinigami.core.data.source.CharacterFavouriteRepository
import io.audioshinigami.core.data.source.CharacterFavouriteRepositoryImpl
import io.audioshinigami.core.data.source.local.CharacterFavouriteDao
import io.audioshinigami.core.data.source.local.CharacterFavouriteLocalDataSource
import io.audioshinigami.core.data.source.local.RickAndMortyDatabase
import io.audioshinigami.core.network.ApiFactory
import io.audioshinigami.core.network.services.RickAndMortyService
import io.audioshinigami.core.utils.CHARACTER_TABLE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModuleBinds {

    @get:Binds
    val CharacterFavouriteRepositoryImpl.repository: CharacterFavouriteRepository

    @get:Binds
    val CharacterFavouriteLocalDataSource.localDataSource: CharacterFavouriteDataSource

    companion object {

        @Provides
        @Singleton
        fun provideRetrofit(): RickAndMortyService = ApiFactory.provideRickAndMortyService()

        @Provides
        @Singleton
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Singleton
        fun provideCharacterFavouriteDao(
            @ApplicationContext context: Context
        ): CharacterFavouriteDao = Room.databaseBuilder(
            context.applicationContext,
            RickAndMortyDatabase::class.java,
            CHARACTER_TABLE
        ).build().characterFavouriteDao()
    }
}
