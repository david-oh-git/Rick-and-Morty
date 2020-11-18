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
package io.audioshinigami.characters_list.list.paging

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.paging.PageKeyedDataSource
import io.audioshinigami.characters_list.list.di.CorotineScopeIo
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.core.network.repositories.RickAndMortyRepository
import io.audioshinigami.core.network.responses.BaseListResponse
import io.audioshinigami.core.network.responses.characters.Character
import javax.inject.Inject
import kotlin.properties.Delegates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

const val PAGE_INIT_ELEMENT = 0
const val PAGE_MAX_ELEMENTS = 20

@ExperimentalCoroutinesApi
open class CharactersPageDataSource @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val repository: RickAndMortyRepository,
    @VisibleForTesting(otherwise = PRIVATE)
    @CorotineScopeIo val scope: CoroutineScope

) : PageKeyedDataSource<Int, Character>() {

    val networkStateFlow = MutableStateFlow<NetworkState>(NetworkState.Loading())

    @VisibleForTesting(otherwise = PRIVATE)
    var retry: (() -> Unit)? = null

    var MAX_PAGE_NUMBER by Delegates.notNull<Int>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        networkStateFlow.value = NetworkState.Loading()

        scope.launch {

            try {

                val response = repository.getCharacters(PAGE_INIT_ELEMENT)
                MAX_PAGE_NUMBER = response.info.pages
                val data = response.results
                callback.onResult(data, null, PAGE_INIT_ELEMENT + 1)
                networkStateFlow.value = NetworkState.Success(isEmptyResponse = data.isEmpty())

                retry = null
            } catch (throwable: Throwable) {
                retry = {
                    loadInitial(params, callback)
                }
                networkStateFlow.value = NetworkState.Error()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        networkStateFlow.value = NetworkState.Loading(true)

        scope.launch {

            try {

                val response = repository.getCharacters(params.key)
                val data = response.results
                val nextKey = if (params.key >= MAX_PAGE_NUMBER) null else (params.key + 1)
                callback.onResult(data, nextKey)

                retry = null

                if (nextKey != null) {
                    networkStateFlow.value = NetworkState.Success(
                        true, data.isEmpty()
                    )
                } else {
                    networkStateFlow.value = NetworkState.Success(
                        isAdditional = true, isEmptyResponse = true
                    )
                }
            } catch (throwable: Throwable) {
                retry = {
                    loadAfter(params, callback)
                }
                networkStateFlow.value = NetworkState.Error(isAdditional = true)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        // Not required
    }

    /**
     *  Retry last fetch operation.
     */
    fun retry() {
        val previousRetry = retry
        retry = null

        previousRetry?.invoke()
    }

    /**
     * Extract list of [Character] items from [BaseListResponse].
     */
    @VisibleForTesting(otherwise = PRIVATE)
    val BaseListResponse<Character>.results
        get() = this.data.results
}
