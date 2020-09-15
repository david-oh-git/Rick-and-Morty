package io.audioshinigami.core.network.responses

import io.audioshinigami.core.annotations.OpenForTesting

/**
 * Rick and Morty response format for single item.
 *
 * @param data Single item received from API.
 */
@OpenForTesting
data class SingleDataResponse<T>(
    val data: T
)