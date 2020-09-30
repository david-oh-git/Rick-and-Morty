package io.audioshinigami.core.network.services

import io.audioshinigami.core.network.responses.BaseListResponse
import io.audioshinigami.core.network.responses.BaseResponse
import io.audioshinigami.core.network.responses.DataResponse
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.core.network.responses.episodes.Episode
import io.audioshinigami.core.network.responses.locations.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Rick and Morty API retrofit service
 */
interface RickAndMortyService {

    /**
     *  Fetches a single character from the API
     *  @param id A character's unique id.
     */
    @GET("$CHARACTER_ENDPOINT{id}")
    suspend fun getCharacter(
        @Path("id") id: Long
    ): BaseResponse<Character>

    /**
     * Fetches a list of all characters.
     *
     * @param page The page number.
     */
    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacters(
        @Query("page") page: Int
    ): DataResponse<Character>

    /**
     *  Fetches a list of characters based on filter parameters
     *
     *  @param name The name of the character.
     *  @param status The status of the character. dead || alive || unknown
     *  @param species The specie of the character.
     *  @param type The type of the character.
     *  @param gender The character's gender.
     */
    @GET(CHARACTER_ENDPOINT)
    suspend fun findCharacters(
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("species") species: String,
        @Query("type") type: String,
        @Query("gender") gender: String
    ):BaseListResponse<Character>

    /**
     *  Fetches a single location from the API
     *  @param id A location's unique id.
     */
    @GET("$LOCATION_ENDPOINT{id}")
    suspend fun getLocation(
        @Path("id") id: Long
    ): BaseResponse<Location>

    /**
     * Fetches a list of all locations.
     *
     * @param page The page number.
     */
    @GET(LOCATION_ENDPOINT)
    suspend fun getLocations(
        @Query("page") page: Int
    ): BaseListResponse<Location>

    /**
     *  Fetches a list of locations based on filter parameters
     *
     *  @param name The name of the location.
     *  @param type The type of location.
     *  @param dimension The location's dimension.
     */
    @GET(LOCATION_ENDPOINT)
    suspend fun findLocations(
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String
    ):BaseListResponse<Location>

    /**
     *  Fetches a single episode from the API
     *  @param id A episode's unique id.
     */
    @GET("$EPISODE_ENDPOINT{id}")
    suspend fun getEpisode(
        @Path("id") id: Long
    ): BaseResponse<Episode>

    /**
     * Fetches a list of all episodes.
     *
     * @param page The page number.
     */
    @GET(EPISODE_ENDPOINT)
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): BaseListResponse<Episode>

    /**
     *  Fetches a list of episodes based on filter parameters
     *
     *  @param name The name of the episode.
     *  @param episode The code for the episode.
     */
    @GET(EPISODE_ENDPOINT)
    suspend fun findEpisodes(
        @Query("name") name: String,
        @Query("episode") episode: String
    ):BaseListResponse<Location>

    companion object {
        private const val CHARACTER_ENDPOINT = "character/"
        private const val LOCATION_ENDPOINT = "location/"
        private const val EPISODE_ENDPOINT = "episode/"
    }
}