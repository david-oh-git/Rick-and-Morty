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
import androidx.paging.DataSource
import io.audioshinigami.core.network.responses.characters.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Provider

/**
 *  Factory for data source,
 */
@ExperimentalCoroutinesApi
class CharactersPageDataSourceFactory @Inject constructor(
    @get:VisibleForTesting
    val providerDataSource: Provider<CharactersPageDataSource>
) : DataSource.Factory<Int, Character>() {

    var sourceFlow = MutableStateFlow<CharactersPageDataSource?>(null)

    override fun create(): DataSource<Int, Character> {
        val dataSource = providerDataSource.get()
        sourceFlow.value = dataSource
        return dataSource
    }

    /**
     *  Refresh data source.
     */
    fun refresh() {
        sourceFlow.value?.invalidate()
    }

    /**
     * Force retry the last fetch on data source.
     */
    fun retry() {
        sourceFlow.value?.retry()
    }
}
