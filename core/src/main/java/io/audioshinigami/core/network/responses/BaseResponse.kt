package io.audioshinigami.core.network.responses

/**
 * Network response for data type [T]
 *
 * @param code The HTTP status code of the response.
 * @param status Description of the API call status.
 * @param message Detailed description if API call fails.
 * @param data The results by API call.
 */
data class BaseResponse<T>(
    val code: Any,
    val status: String,
    val message: String,
    val data: SingleDataResponse<T>
)
