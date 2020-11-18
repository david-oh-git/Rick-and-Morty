/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2020 $today.hour24:$today.minute   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
    ) : NetworkState()

    /**
     * Network loading state.
     *
     * @param isAdditional If request is an additional request.
     */
    data class Loading(
        val isAdditional: Boolean = false
    ) : NetworkState()

    /**
     * Network error state.
     *
     * @param isAdditional If request is an additional request.
     */
    data class Error(
        val isAdditional: Boolean = false
    ) : NetworkState()

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
