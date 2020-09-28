package io.audioshinigami.characters_list.list.paging

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.audioshinigami.characters_list.list.di.CorotineScopeIo
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.core.network.repositories.RickAndMortyRepository
import io.audioshinigami.core.network.responses.BaseListResponse
import io.audioshinigami.core.network.responses.characters.Character
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val PAGE_INIT_ELEMENT = 0
const val PAGE_MAX_ELEMENTS = 20

@ExperimentalCoroutinesApi
open class CharactersPageDataSource @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE )
    val repository: RickAndMortyRepository,
    @VisibleForTesting(otherwise = PRIVATE )
    @CorotineScopeIo val scope: CoroutineScope

): PageKeyedDataSource<Int, Character>() {

    val networkState = MutableLiveData<NetworkState>()
    val networkStateFlow = MutableStateFlow<NetworkState>(NetworkState.Loading())

    @VisibleForTesting(otherwise = PRIVATE )
    var retry: ( () -> Unit )? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        networkState.postValue( NetworkState.Loading())
        networkStateFlow.value = NetworkState.Loading()


        scope.launch(
            CoroutineExceptionHandler { _, throwable ->
                retry = {
                    loadInitial(params, callback)
                }
                networkState.postValue(NetworkState.Error() )
                networkStateFlow.value = NetworkState.Error()
                Timber.d("Error loading data. \n Error msg: ${throwable.message}")
                Timber.d("Error :\n ${throwable.printStackTrace()}")
            }

        ) {
            val response = repository.getCharacters(PAGE_INIT_ELEMENT)
            callback.onResult(response.results, null , PAGE_MAX_ELEMENTS)
            networkState.postValue( NetworkState.Success( isEmptyResponse = response.results.isEmpty()))
            networkStateFlow.value = NetworkState.Success( isEmptyResponse = response.results.isEmpty() )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        networkState.postValue( NetworkState.Loading())
        networkStateFlow.value = NetworkState.Loading()
        scope.launch(
            CoroutineExceptionHandler { _, throwable ->
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(NetworkState.Error() )
                networkStateFlow.value = NetworkState.Error()
                Timber.d("Error loading data. \n Error msg: ${throwable.message}")
                Timber.d("Error :\n ${throwable.printStackTrace()}")
            }

        ) {
            val response = repository.getCharacters(params.key)
            callback.onResult(response.results, params.key + 1)
            networkState.postValue( NetworkState.Success( isEmptyResponse = response.results.isEmpty()))
            networkStateFlow.value = NetworkState.Success( isEmptyResponse = response.results.isEmpty() )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        // Not required
    }

    /**
     *  Retry last fetch operation.
     */
    fun retry() = retry?.invoke()

    /**
     * Extract list of [Character] items from [BaseListResponse].
     */
    @VisibleForTesting(otherwise = PRIVATE)
    val BaseListResponse<Character>.results
        get() = this.data.results
}