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
package io.audioshinigami.characters_list.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import io.audioshinigami.characters_list.list.paging.CharactersPageDataSourceFactory
import io.audioshinigami.characters_list.list.paging.PAGE_MAX_ELEMENTS
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.ui.livedata.SingleLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CharactersListViewModel @Inject constructor(
    @get:VisibleForTesting
    val dataSourceFactory: CharactersPageDataSourceFactory
) : ViewModel() {

    @VisibleForTesting
    val source = liveData {
        dataSourceFactory.sourceFlow
            .collect { emit(it) }
    }

    @VisibleForTesting
    val networkState: LiveData<NetworkState> = source.switchMap {
        it?.networkStateFlow?.asLiveData(viewModelScope.coroutineContext)
    }

    val event = SingleLiveData<CharactersListViewEvent>()
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = networkState.map {

        when (it) {

            is NetworkState.Success -> {
                if (it.isAdditional && it.isEmptyResponse) {
                    CharactersListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    CharactersListViewState.Empty
                } else {
                    CharactersListViewState.Loaded
                }
            }

            is NetworkState.Loading -> {
                if (it.isAdditional) {
                    CharactersListViewState.AddLoading
                } else {
                    CharactersListViewState.Loading
                }
            }

            is NetworkState.Error -> {
                if (it.isAdditional) {
                    CharactersListViewState.AddError
                } else {
                    CharactersListViewState.Error
                }
            }
        }
    }

    fun refreshLoadedCharacterList() {
        dataSourceFactory.refresh()
    }

    @ExperimentalCoroutinesApi
    fun retryAddCharactersList() {
        dataSourceFactory.retry()
    }

    /**
     * To send an open character detail view from the selected character.
     *
     * @param character all the character's details passed on to the character detail view.
     */
    fun openCharacterDetail(character: Character) {
        event.postValue(CharactersListViewEvent.OpenCharacterDetail(character))
    }

    private fun assignCharacterListViewState(networkState: NetworkState) =
        when (networkState) {

            is NetworkState.Success -> {
                if (networkState.isAdditional && networkState.isEmptyResponse) {
                    CharactersListViewState.NoMoreElements
                } else if (networkState.isEmptyResponse) {
                    CharactersListViewState.Empty
                } else {
                    CharactersListViewState.Loaded
                }
            }

            is NetworkState.Loading -> {
                if (networkState.isAdditional) {
                    CharactersListViewState.AddLoading
                } else {
                    CharactersListViewState.Loading
                }
            }

            is NetworkState.Error -> {
                if (networkState.isAdditional) {
                    CharactersListViewState.AddError
                } else {
                    CharactersListViewState.Error
                }
            }
        }
}
