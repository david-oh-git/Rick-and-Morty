package io.audioshinigami.characters_list.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource.LoadCallback
import androidx.paging.PageKeyedDataSource.LoadInitialCallback
import androidx.paging.PageKeyedDataSource.LoadInitialParams
import androidx.paging.PageKeyedDataSource.LoadParams
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.core.network.repositories.RickAndMortyRepository
import io.audioshinigami.core.network.responses.DataResponse
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.test_utils.MainCoroutineRule
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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

    interface Callback : () -> Unit

    @MockK(relaxed = true)
    lateinit var repository: RickAndMortyRepository

    @MockK(relaxed = true)
    lateinit var networkState: MutableLiveData<NetworkState>

    @MockK(relaxed = true)
    lateinit var retry: Callback

    @InjectMockKs(injectImmutable = true, overrideValues = true)
    lateinit var dataSource: CharactersPageDataSource

    private var scope = CoroutineScope(Dispatchers.Main)

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun loadInitial_ShouldPostLoadingState() {
        val params = mockk<LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, Character>>()
        dataSource.loadInitial(params, callback)

        verify { networkState.postValue(NetworkState.Loading()) }
    }

    @Test
    fun loadInitial_WithError_ShouldPostErrorState() {
        val params = mockk<LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, Character>>()
        dataSource.loadInitial(params, callback)

        Assert.assertNotNull(retry)
        verify { networkState.postValue(NetworkState.Error()) }
    }

    @Test
    fun loadInitial_WithSuccessEmptyData_ShouldPostEmptySuccessState() {
        val params = LoadInitialParams<Int>(100, false)
        val callback = mockk<LoadInitialCallback<Int, Character>>(relaxed = true)
        val emptyData = emptyList<Character>()
        val response = mockk<DataResponse<Character>>()

        coEvery { repository.getCharacters(any()) } returns response

        dataSource.loadInitial(params, callback)

        coVerify { repository.getCharacters(0) }
        verify { callback.onResult(emptyData, null, PAGE_MAX_ELEMENTS) }
        verify {
            networkState.postValue(
                NetworkState.Success(
                    isAdditional = false,
                    isEmptyResponse = true
                )
            )
        }
    }

    @Test
    fun loadInitial_WithSuccessData_ShouldPostNonEmptySuccessState() {
        val params = LoadInitialParams<Int>(0, true)
        val callback = mockk<LoadInitialCallback<Int, Character>>(relaxed = true)
        val data = listOf(mockk<Character>())
        val response = mockk<DataResponse<Character>>()

        coEvery { repository.getCharacters(any()) } returns response

        dataSource.loadInitial(params, callback)

        coVerify { repository.getCharacters(0) }
        verify { callback.onResult(data, null, PAGE_MAX_ELEMENTS) }
        verify { networkState.postValue(NetworkState.Success()) }
    }

    @Test
    fun loadAfter_ShouldPostAdditionalLoadingState() {
        val params = mockk<LoadParams<Int>>()
        val callback = mockk<LoadCallback<Int, Character>>()
        dataSource.loadAfter(params, callback)

        verify { networkState.postValue(NetworkState.Loading(true)) }
    }

    @Test
    fun loadAfter_WithError_ShouldPostAdditionalErrorState() {
        val params = LoadParams(0, 0)
        val callback = mockk<LoadCallback<Int, Character>>()
        dataSource.loadAfter(params, callback)

        Assert.assertNotNull(retry)
        verify { networkState.postValue(NetworkState.Error(true)) }
    }

    @Test
    fun loadAfter_WithSuccessEmptyData_ShouldPostAdditionalEmptySuccessState() {
        val paramKey = 100
        val params = LoadParams(paramKey, 0)
        val callback = mockk<LoadCallback<Int, Character>>(relaxed = true)
        val emptyData = emptyList<Character>()
        val response = mockk<DataResponse<Character>>()

        coEvery { repository.getCharacters(any()) } returns response

        dataSource.loadAfter(params, callback)

        coVerify { repository.getCharacters(paramKey) }
        verify { callback.onResult(emptyData, paramKey + PAGE_MAX_ELEMENTS) }
        verify {
            networkState.postValue(
                NetworkState.Success(
                    isAdditional = true,
                    isEmptyResponse = true
                )
            )
        }
    }

    @Test
    fun loadAfter_WithSuccessData_ShouldPostAdditionalNonEmptySuccessState() {
        val paramKey = 0
        val params = LoadParams(paramKey, 0)
        val callback = mockk<LoadCallback<Int, Character>>(relaxed = true)
        val data = listOf(mockk<Character>())
        val response = mockk<DataResponse<Character>>()

        coEvery { repository.getCharacters(any()) } returns response

        dataSource.loadAfter(params, callback)

        coVerify { repository.getCharacters(paramKey) }
        verify { callback.onResult(data, paramKey + PAGE_MAX_ELEMENTS) }
        verify {
            networkState.postValue(
                NetworkState.Success(
                    isAdditional = true,
                    isEmptyResponse = false
                )
            )
        }
    }

    @Test
    fun loadBefore_ShouldDoNothing() {
        val params = mockk<LoadParams<Int>>()
        val callback = mockk<LoadCallback<Int, Character>>()
        dataSource.loadBefore(params, callback)

        verify { params wasNot Called }
        verify { callback wasNot Called }
    }
}
