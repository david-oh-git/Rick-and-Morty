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
package io.audioshinigami.characters.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.audioshinigami.characters.detail.models.CharacterDetail
import io.audioshinigami.characters.detail.models.CharacterFavouriteMapper
import io.audioshinigami.core.data.source.CharacterFavouriteRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

class CharacterDetailViewModel @Inject constructor(
    @get:VisibleForTesting
    val repository: CharacterFavouriteRepository,
    @get:VisibleForTesting
    val characterFavouriteMapper: CharacterFavouriteMapper
) : ViewModel() {

    private val _data = MutableLiveData<CharacterDetail>()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val data: LiveData<CharacterDetail>
        get() = _data

    private val _state = MutableLiveData<CharacterDetailViewState>()

    @VisibleForTesting
    val state: LiveData<CharacterDetailViewState>
        get() = _state

    init {

        viewModelScope.launch {
            _state.postValue(CharacterDetailViewState.Loading)
        }
    }

    fun addCharacterToFavorite() {

        data.value?.let {
            viewModelScope.launch {
                val fav = characterFavouriteMapper.transform(it)
                repository.save(fav)
                _state.postValue(CharacterDetailViewState.AddedToFavorite)
            }
        }
    }

    fun dismissCharacterDetail() {
        _state.postValue(CharacterDetailViewState.Dismiss)
    }

    /**
     * Set [CharacterDetail] value from [CharacterDetailFragment] args
     * and also update [state] value if it is already on the database.
     */
    fun setData(characterDetail: CharacterDetail) = viewModelScope.launch {
        _data.postValue(characterDetail)
        _state.postValue(CharacterDetailViewState.AddToFavorite)
        if (repository.search(characterDetail.name))
            _state.postValue(CharacterDetailViewState.AddedToFavorite)
    }
}
