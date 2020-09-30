package io.audioshinigami.characters_list.list

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import io.audioshinigami.characters_list.list.paging.CharactersPageDataSourceFactory
import io.audioshinigami.characters_list.list.paging.PAGE_MAX_ELEMENTS
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.ui.livedata.SingleLiveData
import javax.inject.Inject
import timber.log.Timber

class CharactersListViewModel @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val dataSourceFactory: CharactersPageDataSourceFactory
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val event = SingleLiveData<CharactersListViewEvent>()
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {

        Timber.d("state is $it")
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

    fun retryAddCharactersList() {
        dataSourceFactory.retry()
    }

    fun openCharacterDetail(characterId: Int) {
        event.postValue(CharactersListViewEvent.OpenCharacterDetail(characterId))
    }
}