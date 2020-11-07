/*
 * MIT License
 *
 * Copyright (c) 7/11/2020 12:28   David Osemwota.
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

package io.audioshinigami.favourites_list

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.core.data.source.CharacterFavouriteRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

internal class FavouriteListViewModel @Inject constructor(
        @VisibleForTesting(otherwise = PRIVATE)
        val repository: CharacterFavouriteRepository
): ViewModel() {

    val data: LiveData<List<CharacterFavourite>> = liveData {
        repository.getAllCharacterFlow()
                .collect {
                    emit(it)
                }
    }

    val state = Transformations.map(data){
        if(it.isEmpty())
            FavouriteListViewState.Empty
        else
            FavouriteListViewState.Listed
    }
}