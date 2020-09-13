package io.audioshinigami.core.network.responses.episodes

import com.google.gson.annotations.SerializedName
import io.audioshinigami.core.network.responses.EndPoint

/**
 *  Rick and Morty episode API.
 *  @param id Unique id for the episode.
 *  @param name Title of the episode.
 *  @param airDate The date the episode was aired.
 */
data class Episode(
    val id: Int,
    val name: String,

    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val characters: List<EndPoint>,
    val url: String,
    val created: String
)