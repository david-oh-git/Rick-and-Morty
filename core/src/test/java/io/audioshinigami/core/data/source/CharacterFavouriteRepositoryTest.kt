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
package io.audioshinigami.core.data.source

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.core.util.CharacterFavouriteFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharacterFavouriteRepositoryTest {

    @Mock
    lateinit var localDataSource: CharacterFavouriteDataSource
    lateinit var characterFavouriteRepository: CharacterFavouriteRepository

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        characterFavouriteRepository = CharacterFavouriteRepositoryImpl(
            localDataSource,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun getAllCharacterFavourites_shouldInvokeCorrectLocalDataSourceMethod() = runBlockingTest {
        characterFavouriteRepository.getAllCharacters()

        verify(localDataSource).getAllCharacters()
    }

    @Test
    fun getAllCharacterFavouritesFlow_shouldInvokeCorrectLocalDataSourceMethod() = runBlockingTest {
        characterFavouriteRepository.getAllCharacterFlow()

        verify(localDataSource).getAllCharacterFlow()
    }

    @Test
    fun deleteAllCharacterFavourites_shouldInvokeCorrectLocalDataSourceMethod() = runBlockingTest {
        characterFavouriteRepository.deleteAllCharacters()

        verify(localDataSource).deleteAllCharacters()
    }

    @Test
    fun saveAllCharacterFavourites_shouldInvokeCorrectLocalDataSourceMethod() = runBlockingTest {
        val characterFavourite = CharacterFavouriteFactory.getCharacter()
        characterFavouriteRepository.save(characterFavourite)

        val characterSavedCaptor = argumentCaptor<CharacterFavourite>()
        verify(localDataSource).save(characterSavedCaptor.capture())
        assertThat(characterFavourite).isEqualTo(characterSavedCaptor.lastValue)
    }

    @Test
    @DisplayName("verify calling repository deleteCharacter calls localDataSource method")
    fun deleteById_confirmResults() = runBlockingTest {
        val id = 34L
        val characterFavourite = CharacterFavouriteFactory.getCharacter().copy(id = id)
        characterFavouriteRepository.save(characterFavourite)
        characterFavouriteRepository.deleteCharacterFavourite(id)

        val savedCharacterId = argumentCaptor<Long>()
        verify(localDataSource).deleteCharacterFavourite(savedCharacterId.capture())
        assertThat(id).isEqualTo(savedCharacterId.lastValue)
    }

    @Test
    @DisplayName(
        "verify calling repository.search calls localDataSource.search with same " +
            "argument"
    )
    fun searchCharacter() = runBlockingTest {
        val id = 67L
        val name = "Soifon"
        val characterFavourite = CharacterFavouriteFactory.getCharacter().copy(id = id, name = name)
        characterFavouriteRepository.save(characterFavourite)
        characterFavouriteRepository.search(name)

        val nameArgument = argumentCaptor<String>()
        verify(localDataSource).search(nameArgument.capture())
        assertThat(name).isEqualTo(nameArgument.lastValue)
    }
}
