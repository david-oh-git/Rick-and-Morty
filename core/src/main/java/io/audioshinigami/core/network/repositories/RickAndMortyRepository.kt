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

import androidx.annotation.VisibleForTesting
import io.audioshinigami.core.annotations.OpenForTesting
import io.audioshinigami.core.network.responses.BaseResponse
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.core.network.services.RickAndMortyService
import javax.inject.Inject

@OpenForTesting
class RickAndMortyRepository @Inject constructor(
    @get:VisibleForTesting
    internal val service: RickAndMortyService
) {

    /**
     * Fetch a character's info
     *
     * @param id The character's id.
     */
    suspend fun getCharacter(id: Long): BaseResponse<Character> =
        service.getCharacter(id)

    /**
     * Fetch all characters in pages of 20 characters each.
     *
     * @param page The page number of the request
     */
    suspend fun getCharacters(page: Int) =
        service.getCharacters(page)

    /**
     * Fetches a list of characters by filtering with
     * parameters.
     *
     * @param name Name of the character.
     * @param gender Gender of the character.
     * @param species The character's specie.
     */
    suspend fun findCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ) =
        service.findCharacters(name, status, species, type, gender)

    /**
     * Fetch a location's info
     *
     * @param id The location's id.
     */
    suspend fun getLocation(id: Long) = service.getLocation(id)

    /**
     * Fetch all locations in pages of 20 locations each.
     *
     * @param page The page number of the request
     */
    suspend fun getLocations(page: Int) = service.getLocations(page)

    /**
     * Fetches a list of locations by filtering with
     * parameters.
     *
     * @param name Name of the location.
     * @param type Type of location.
     * @param dimension The dimension of the location. eg C137.
     */
    suspend fun findLocations(
        name: String,
        type: String,
        dimension: String
    ) =
        service.findLocations(name, type, dimension)

    /**
     * Fetch a episode's info
     *
     * @param id The episode's id.
     */
    suspend fun getEpisode(id: Long) = service.getEpisode(id)

    /**
     * Fetch all episodes in pages of 20 episodes each.
     *
     * @param page The page number of the request
     */
    suspend fun getEpisodes(page: Int) = service.getEpisodes(page)

    /**
     * Fetches a list of locations by filtering with
     * parameters.
     *
     * @param name Name of the episode.
     * @param episodes Code of the episodes. eg S01E04
     */
    suspend fun findEpisodes(
        name: String,
        episodes: String
    ) = service.findEpisodes(name, episodes)
}
