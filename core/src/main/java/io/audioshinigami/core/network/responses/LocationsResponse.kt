package io.audioshinigami.core.network.responses

import io.audioshinigami.core.network.responses.locations.Location

/**
 * Rick and Morty Api response for locations
 *
 * @param info Details about the response
 * @param results List of [Location] from API.
 */
data class LocationsResponse(
    val info: Info,
    val results: List<Location>
)