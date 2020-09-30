package io.audioshinigami.core.network.responses

import io.audioshinigami.core.annotations.OpenForTesting

/**
 * Network response for list of data type [T]
 *
 * @param code The HTTP status code of the response.
 * @param status Description of the API call status.
 * @param message Detailed description if API call fails.
 * @param data The results by API call.
 */
@OpenForTesting
data class BaseListResponse<T>(
    val code: Any,
    val status: String,
    val message: String,
    val data: DataResponse<T>
)
