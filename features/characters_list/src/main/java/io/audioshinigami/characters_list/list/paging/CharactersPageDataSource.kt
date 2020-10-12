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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

const val PAGE_INIT_ELEMENT = 0
const val PAGE_MAX_ELEMENTS = 20

@ExperimentalCoroutinesApi
open class CharactersPageDataSource @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val repository: RickAndMortyRepository,
    @VisibleForTesting(otherwise = PRIVATE)
    @CorotineScopeIo val scope: CoroutineScope

) : PageKeyedDataSource<Int, Character>() {

    val networkState = MutableLiveData<NetworkState>()
    val networkStateFlow = MutableStateFlow<NetworkState>(NetworkState.Loading())

    @VisibleForTesting(otherwise = PRIVATE)
    var retry: (  () -> Unit)? = null

    var MAX_PAGE_NUMBER by Delegates.notNull<Int>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        networkState.postValue(NetworkState.Loading())
        networkStateFlow.value = NetworkState.Loading()

        scope.launch {

            try {

                val response = repository.getCharacters(PAGE_INIT_ELEMENT)
                MAX_PAGE_NUMBER = response.info.pages
                Timber.d("params Max key is: $MAX_PAGE_NUMBER")
                val data = response.results
                callback.onResult(data, null, PAGE_INIT_ELEMENT + 1)

                networkState.postValue(NetworkState.Success(isEmptyResponse = data.isEmpty()))
                networkStateFlow.value = NetworkState.Success(isEmptyResponse = data.isEmpty())

                retry = null

            }catch (throwable: Throwable){
                retry = {
                    Timber.d("Initial retry is called.")
                    loadInitial(params, callback)
                }
                networkState.postValue(NetworkState.Error())
                networkStateFlow.value = NetworkState.Error()
            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        networkState.postValue(NetworkState.Loading(true))
        networkStateFlow.value = NetworkState.Loading(true)

        scope.launch {

            try{

                val response = repository.getCharacters(params.key)
                val data = response.results
                Timber.d("data size is ${data.size}")
                val nextKey = if( params.key >= MAX_PAGE_NUMBER) null else (params.key + 1 )
                Timber.d("Params key is : $nextKey")
                callback.onResult(data, nextKey)

                retry = null

                if( nextKey != null ){
                    networkState.postValue(NetworkState.Success(
                        true, data.isEmpty())
                    )
                    networkStateFlow.value = NetworkState.Success(
                        true, data.isEmpty()
                    )
                }else {
                    networkState.postValue(NetworkState.Success(
                        isAdditional = true,  isEmptyResponse = true )
                    )
                    networkStateFlow.value = NetworkState.Success(
                        isAdditional = true,  isEmptyResponse = true
                    )
                }


            }catch (throwable: Throwable){
                retry = {
                    Timber.d("After retry is called.")
                    loadAfter(params, callback)
                }

                networkState.postValue(NetworkState.Error(isAdditional = true))
                networkStateFlow.value = NetworkState.Error(isAdditional = true)
                Timber.d("Error loading after data. \n Error msg: ${throwable.message}")
                Timber.d("Error :\n ${throwable.printStackTrace()}")
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        // Not required
    }

    /**
     *  Retry last fetch operation.
     */
    fun retry(){
        Timber.d("calling retry")
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
