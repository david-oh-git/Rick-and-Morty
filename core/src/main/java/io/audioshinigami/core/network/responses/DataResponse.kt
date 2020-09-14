package io.audioshinigami.core.network.responses

/**
 * Rick and Morty API response format for list of items
 *
 * @param info Information about the response specified by [Info]
 * @param results The list of [T] returned by the API.
 */
data class DataResponse<T>(
    val info: Info,
    val results: List<T>
)