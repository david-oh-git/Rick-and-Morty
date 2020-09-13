package io.audioshinigami.core.network.responses

import io.audioshinigami.core.network.responses.episodes.Episode

/**
 * Rick and Morty Api response for episodes.
 *
 * @param info Details about the response
 * @param results List of [Episode]s from API.
 */
data class EpisodesResponse(
    val info: Info,
    val results: List<Episode>
)