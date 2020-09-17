package io.audioshinigami.core.network

/**
 *  Represent various states of any network request.
 */
sealed class NetworkState {

    /**
     *  Successful network state.
     *
     *  @param isAdditional If request is an additional request.
     *  @param isEmptyResponse If request body is empty.
     */
    data class Success(
        val isAdditional: Boolean = false,
        val isEmptyResponse: Boolean = false
    ): NetworkState()

    /**
     * Network loading state.
     *
     * @param isAdditional If request is an additional request.
     */
    data class Loading(
        val isAdditional: Boolean = false
    ): NetworkState()

    /**
     * Network error state.
     *
     * @param isAdditional If request is an additional request.
     */
    data class Error(
        val isAdditional: Boolean = false
    ): NetworkState()

    /**
     * If current network state is [Success].
     *
     * @return True if it is success state.
     */
    fun isSuccess() = this is Success

    /**
     * If current network state is [Loading].
     *
     * @return True if it is loading state.
     */
    fun isLoading() = this is Loading

    /**
     * If current network state is [Error].
     *
     * @return True if it is error state.
     */
    fun isError() = this is Error
}