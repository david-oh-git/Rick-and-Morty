/*
 * MIT License
 *
 * Copyright (c) 2020 David Osemwota.
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

package io.audioshinigami.core.data.source.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.core.util.CharacterFavouriteFactory
import io.audioshinigami.test_utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
internal class CharacterFavouriteDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var database: RickAndMortyDatabase
    private lateinit var characterFavouriteDao: CharacterFavouriteDao

    @Before
    fun init(){
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            applicationContext,
            RickAndMortyDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        characterFavouriteDao = database.characterFavouriteDao()
    }

    @After
    fun reset(){
        database.close()
    }

    @Test
    fun saveCharacter_getCharacter() = runBlockingTest {
        // Arrange: save a characterFavourite
        val characterFavourite = CharacterFavouriteFactory.getCharacter()
        characterFavouriteDao.save(characterFavourite)

        // Act: Get all characters from the database.
        val result = characterFavouriteDao.getAllCharacters()

        // Assert: confirm results
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].created).isEqualTo(characterFavourite.created)
        assertThat(result[0].id).isEqualTo(characterFavourite.id)
        assertThat(result[0].gender).isEqualTo(characterFavourite.gender)
        assertThat(result[0].locationName).isEqualTo(characterFavourite.locationName)
        assertThat(result[0].originName).isEqualTo(characterFavourite.originName)
    }

    @Test
    fun deleteDatabase_getEmptyDatabase() = runBlockingTest {
        // Arrange: save a characterFavourite
        val characterFavourite = CharacterFavouriteFactory.getCharacter()
        characterFavouriteDao.save(characterFavourite)

        // Act: Delete all characters from the database.
        characterFavouriteDao.deleteAllCharacters()
        val result = characterFavouriteDao.getAllCharacters()

        // Assert: confirm results
        assertThat(result.size).isEqualTo(0)
        assertThat(result.isEmpty()).isTrue()
    }
}