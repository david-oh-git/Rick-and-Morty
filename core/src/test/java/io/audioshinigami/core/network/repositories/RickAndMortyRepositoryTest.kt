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
package io.audioshinigami.core.network.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import io.audioshinigami.core.network.services.RickAndMortyService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class RickAndMortyRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: RickAndMortyService
    private lateinit var rickAndMortyRepository: RickAndMortyRepository

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        rickAndMortyRepository = RickAndMortyRepository(service)
    }

    @Test
    @DisplayName(
        "When repository.getCharacters is called , service.getCharacters is also " +
            "called with the same args"
    )
    fun getCharacters() = runBlockingTest {
        // Arrange:
        val page = 5
        val pageArg = argumentCaptor<Int>()
        rickAndMortyRepository.getCharacters(page)

        verify(service).getCharacters(pageArg.capture())

        assertThat(page).isEqualTo(pageArg.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.getCharacter is called , service.getCharacter is also " +
            "called with the same args"
    )
    fun getCharacter() = runBlockingTest {
        val characterId = 12L
        val capturedId = argumentCaptor<Long>()
        rickAndMortyRepository.getCharacter(characterId)

        verify(service).getCharacter(capturedId.capture())

        assertThat(characterId).isEqualTo(capturedId.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.findCharacters is called , service.findCharacters is also " +
            "called with the same args"
    )
    fun findCharacters() = runBlockingTest {

        val characterName = "Summer Smith"
        val characterStatus = "Alive"
        val characterSpecies = "Human"
        val characterType = "Big Sister lol !"
        val characterGender = "Female"
        val (name, status, species, type, gender) =
            argumentCaptor<String, String, String, String, String>()

        rickAndMortyRepository.findCharacters(
            characterName,
            characterStatus,
            characterSpecies,
            characterType,
            characterGender
        )

        verify(service).findCharacters(
            name = name.capture(),
            status = status.capture(),
            species = species.capture(),
            type = type.capture(),
            gender = gender.capture()
        )

        assertThat(characterName).isEqualTo(name.lastValue)
        assertThat(characterStatus).isEqualTo(status.lastValue)
        assertThat(characterSpecies).isEqualTo(species.lastValue)
        assertThat(characterType).isEqualTo(type.lastValue)
        assertThat(characterGender).isEqualTo(gender.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.getLocations is called , service.getLocations is also " +
            "called with the same args"
    )
    fun getLocations() = runBlockingTest {
        // Arrange:
        val page = 5
        val pageArg = argumentCaptor<Int>()
        rickAndMortyRepository.getLocations(page)

        verify(service).getLocations(pageArg.capture())

        assertThat(page).isEqualTo(pageArg.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.getLocation is called , service.getLocation is also " +
            "called with the same args"
    )
    fun getLocation() = runBlockingTest {
        val locationId = 12L
        val capturedId = argumentCaptor<Long>()
        rickAndMortyRepository.getLocation(locationId)

        verify(service).getLocation(capturedId.capture())

        assertThat(locationId).isEqualTo(capturedId.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.findLocation is called , service.findLocation is also " +
            "called with the same args"
    )
    fun findLocations() = runBlockingTest {

        val locationName = "The Citadel"
        val locationType = "Space station"
        val locationDimension = "C137"
        val (name, type, dimensions) =
            argumentCaptor<String, String, String>()

        rickAndMortyRepository.findLocations(
            locationName,
            locationType,
            locationDimension
        )

        verify(service).findLocations(
            name = name.capture(),
            type = type.capture(),
            dimension = dimensions.capture()
        )

        assertThat(locationName).isEqualTo(name.lastValue)
        assertThat(locationType).isEqualTo(type.lastValue)
        assertThat(locationDimension).isEqualTo(dimensions.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.getEpisodes is called , service.getEpisodes is also" +
            "called with the same args"
    )
    fun getEpisodes() = runBlockingTest {
        // Arrange:
        val page = 5
        val pageArg = argumentCaptor<Int>()
        rickAndMortyRepository.getEpisodes(page)

        verify(service).getEpisodes(pageArg.capture())

        assertThat(page).isEqualTo(pageArg.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.getEpisode is called , service.getEpisode is also" +
            "called with the same args"
    )
    fun getEpisode() = runBlockingTest {
        val episodeId = 12L
        val capturedId = argumentCaptor<Long>()
        rickAndMortyRepository.getEpisode(episodeId)

        verify(service).getEpisode(capturedId.capture())

        assertThat(episodeId).isEqualTo(capturedId.lastValue)
    }

    @Test
    @DisplayName(
        "When repository.findEpisodes is called , service.findEpisodes is also" +
            "called with the same args"
    )
    fun findEpisodes() = runBlockingTest {

        val episodeName = "Close Rick-counters of the Rick Kind"
        val episodeCode = "S01E10"
        val (name, episode) =
            argumentCaptor<String, String>()

        rickAndMortyRepository.findEpisodes(
            episodeName,
            episodeCode
        )

        verify(service).findEpisodes(
            name = name.capture(),
            episode = episode.capture()
        )

        assertThat(episodeName).isEqualTo(name.lastValue)
        assertThat(episodeCode).isEqualTo(episode.lastValue)
    }
}
