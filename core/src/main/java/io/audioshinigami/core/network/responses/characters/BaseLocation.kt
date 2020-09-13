package io.audioshinigami.core.network.responses.characters

/**
 *  Class that Represents a location or origin from API response.
 *
 *  @param name name of the location
 *  @param url The url for the location from API.
 */
data class BaseLocation(
    val name: String,
    val url: String
)