package io.audioshinigami.core.network.responses

/**
 * Rick and Morty response format for single item.
 *
 * @param data Single item received from API.
 */
data class SingleResponse<T>(
    val data: T
)