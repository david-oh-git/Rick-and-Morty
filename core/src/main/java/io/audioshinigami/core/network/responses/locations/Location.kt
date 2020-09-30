package io.audioshinigami.core.network.responses.locations

import io.audioshinigami.core.network.responses.EndPoint

/**
 *  Rick and Morty Api API sub response
 *
 *  @param id Unique id of the location.
 *  @param name Name of the location.
 *  @param type The type of location.
 *  @param dimension The dimension in which the location is located.
 *  @param residents List of characters that has been seen at this location.
 *  @param url Link to the location's endpoint.
 *  @param created Time created on API endpoint.
 */
data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<EndPoint>,
    val url: String,
    val created: String
)
