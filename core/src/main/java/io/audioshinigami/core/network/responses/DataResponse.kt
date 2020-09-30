package io.audioshinigami.core.network.responses

import io.audioshinigami.core.annotations.OpenForTesting

/**
 * Rick and Morty API response format for list of items
 *
 * @param info Information about the response specified by [Info]
 * @param results The list of [T] returned by the API.
 */
@OpenForTesting
data class DataResponse<T>(
    val info: Info,
    val results: List<T>
)
