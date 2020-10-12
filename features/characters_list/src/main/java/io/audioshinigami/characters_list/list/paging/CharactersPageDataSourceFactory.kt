package io.audioshinigami.characters_list.list.paging

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.audioshinigami.core.network.responses.characters.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Provider

/**
 *  Factory for data source,
 */
@ExperimentalCoroutinesApi
class CharactersPageDataSourceFactory @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val providerDataSource: Provider<CharactersPageDataSource>
) : DataSource.Factory<Int, Character>() {

    var sourceLiveData = MutableLiveData<CharactersPageDataSource>()

    override fun create(): DataSource<Int, Character> {
        val dataSource = providerDataSource.get()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    /**
     *  Refresh data source.
     */
    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    /**
     * Force retry the last fetch on data source.
     */
    fun retry() {
        sourceLiveData.value?.retry()
    }
}
