package io.audioshinigami.chracters_list.list

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import io.audioshinigami.chracters_list.list.paging.CharactersPageDataSourceFactory
import io.audioshinigami.chracters_list.list.paging.PAGE_MAX_ELEMENTS
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.ui.livedata.SingleLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CharactersListViewModel(
    @VisibleForTesting(otherwise = PRIVATE)
    val dataSourceFactory: CharactersPageDataSourceFactory
): ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData){
        it.networkState
    }

    val event = SingleLiveData<CharactersListViewEvent>()
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {

        when(it){

            is NetworkState.Success -> {
                if( it.isAdditional && it.isEmptyResponse){
                    CharactersListViewState.NoMoreElements
                }else if (it.isEmptyResponse){
                    CharactersListViewState.Empty
                }else{
                    CharactersListViewState.Loaded
                }
            }

            is NetworkState.Error -> {
                if(it.isAdditional){
                    CharactersListViewState.AddError
                }else{
                    CharactersListViewState.Error
                }
            }

            is NetworkState.Loading -> {
                if(it.isAdditional){
                    CharactersListViewState.AddLoading
                }else{
                    CharactersListViewState.Loading
                }
            }
        }
    }

    fun refreshCharacterList(){
        dataSourceFactory.refresh()
    }

    fun retryAddCharactersList() {
        dataSourceFactory.retry()
    }

    fun openCharacterDetail(characterId: Int) {
        event.postValue(CharactersListViewEvent.OpenCharacterDetail(characterId))
    }
}