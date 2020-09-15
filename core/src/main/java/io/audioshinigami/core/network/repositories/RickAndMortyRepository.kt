package io.audioshinigami.core.network.repositories

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import io.audioshinigami.core.network.responses.BaseResponse
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.core.network.services.RickAndMortyService

class RickAndMortyRepository(
    @VisibleForTesting(otherwise = PRIVATE )
    internal val service: RickAndMortyService
) {

    /**
     * Fetch a character's info
     *
     * @param id The character's id.
     */
    suspend fun getCharater(id: Long): BaseResponse<Character> =
        service.getCharacter(id)

    /**
     * Fetch all characters in pages of 20 characters each.
     *
     * @param page The page number of the request
     */
    suspend fun getCharacters(page:Int) =
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
    )=
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
    suspend fun getEpisodes(page:Int) = service.getEpisodes(page)

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
    ) = service.findEpisodes(name,episodes)

}