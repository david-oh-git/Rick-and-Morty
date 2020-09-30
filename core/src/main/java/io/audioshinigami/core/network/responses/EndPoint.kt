package io.audioshinigami.core.network.responses

/**
 * Rick and Morty Api sub response representing
 *
 * url strings for characters, locations or episodes endpoints
 *
 * @param path URL to the endpoint.
 */
data class EndPoint(
    val path: String
)
