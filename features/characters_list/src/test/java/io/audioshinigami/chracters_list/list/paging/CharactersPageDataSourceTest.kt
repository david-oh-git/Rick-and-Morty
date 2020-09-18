package io.audioshinigami.chracters_list.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PageKeyedDataSource.LoadInitialCallback
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.core.network.repositories.RickAndMortyRepository
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.test_utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CharactersPageDataSourceTest {

    // TODO conclude tests

    /**
     * Set the main coroutine dispatcher for testing.
     */
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    /**
     * Executes tasks synchronously using Architecture components.
     */
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    interface  Callback: () -> Unit

    @MockK(relaxed = true)
    lateinit var repository: RickAndMortyRepository

    @MockK(relaxed = true)
    lateinit var networkState: MutableLiveData<NetworkState>

    @MockK(relaxed = true)
    lateinit var retry: Callback

    @InjectMockKs(injectImmutable = true, overrideValues = true)
    lateinit var dataSource: CharactersPageDataSource

    private var scope = CoroutineScope(Dispatchers.Unconfined)

    @Before
    fun init(){
        MockKAnnotations.init(this)
    }

    @Test
    fun loadInitial_ShouldPostLoadingState() {
        val params = mockk<PageKeyedDataSource.LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, Character>>()
        dataSource.loadInitial(params, callback)

        verify { networkState.postValue(NetworkState.Loading()) }
    }

    @Test
    fun loadInitial_WithError_ShouldPostErrorState() {
        val params = mockk<PageKeyedDataSource.LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, Character>>()
        dataSource.loadInitial(params, callback)

        Assert.assertNotNull(retry)
        verify { networkState.postValue(NetworkState.Error()) }
    }

}