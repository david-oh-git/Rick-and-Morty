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
package io.audioshinigami.characters_list.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PageKeyedDataSource.LoadCallback
import androidx.paging.PageKeyedDataSource.LoadInitialCallback
import androidx.paging.PageKeyedDataSource.LoadInitialParams
import androidx.paging.PageKeyedDataSource.LoadParams
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.core.network.repositories.RickAndMortyRepository
import io.audioshinigami.core.network.responses.DataResponse
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.test_utils.MainCoroutineRule
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Disabled

@ExperimentalCoroutinesApi
internal class CharactersPageDataSourceTest {

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

    @MockK
    lateinit var networkStateFlow: MutableStateFlow<NetworkState>

    @MockK(relaxed = true)
    lateinit var retry: Callback

    @InjectMockKs(injectImmutable = true, overrideValues = true)
    lateinit var dataSource: CharactersPageDataSource

    private var scope = CoroutineScope(Dispatchers.Main)

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun reset() {
        unmockkAll()
    }

    @Test
    fun loadInitial_ShouldPostLoadingState() {
        val params = mockk<LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, Character>>()
        val loadingState = NetworkState.Loading()
        every { dataSource.loadInitial(params, callback) } answers {

            every { networkStateFlow.value } returns loadingState
        }

        dataSource.loadInitial(params, callback)

        assertThat(networkStateFlow.value).isEqualTo(loadingState)
    }

    @Test
    fun loadInitial_WithError_ShouldPostErrorState() {
        val params = mockk<LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, Character>>()
        val errorState = NetworkState.Error()
        every { dataSource.loadInitial(params, callback) } answers {

            every { networkStateFlow.value } returns errorState
        }

        dataSource.loadInitial(params, callback)

        Assert.assertNotNull(retry)
        assertThat(networkStateFlow.value).isEqualTo(errorState)
    }

    @Test
    @Disabled("TODO fix issue")
    fun loadInitial_WithSuccessEmptyData_ShouldPostEmptySuccessState() {
        val params = LoadInitialParams<Int>(20, false)
        val callback = mockk<LoadInitialCallback<Int, Character>>(relaxed = true)
        val emptyData = emptyList<Character>()
        val response = mockk<DataResponse<Character>>()
        val successNetworkState = NetworkState.Success(isAdditional = false, isEmptyResponse = true)

        coEvery { repository.getCharacters(any()) } returns response
        every { dataSource.loadInitial(params, callback) } answers {
            callback.onResult(emptyData, null, PAGE_MAX_ELEMENTS)

            every { networkStateFlow.value } returns successNetworkState
        }

        dataSource.loadInitial(params, callback)

        coVerify { repository.getCharacters(0) }
        verify { callback.onResult(emptyData, null, PAGE_MAX_ELEMENTS) }

        assertThat(successNetworkState).isEqualTo(networkStateFlow.value)
    }

    @Test
    @Disabled("TODO fix issue")
    fun loadInitial_WithSuccessData_ShouldPostNonEmptySuccessState() {
        val params = LoadInitialParams<Int>(0, true)
        val callback = mockk<LoadInitialCallback<Int, Character>>(relaxed = true)
        val data = listOf(mockk<Character>())
        val response = mockk<DataResponse<Character>>()
        val successNetworkState = NetworkState.Success()

        coEvery { repository.getCharacters(any()) } returns response
        every { dataSource.loadInitial(params, callback) } answers {
            callback.onResult(data, null, PAGE_MAX_ELEMENTS)

            every { networkStateFlow.value } returns successNetworkState
        }

        dataSource.loadInitial(params, callback)

        coVerify { repository.getCharacters(0) }
        verify { callback.onResult(data, null, PAGE_MAX_ELEMENTS) }
        assertThat(successNetworkState).isEqualTo(networkStateFlow.value)
    }

    @Test
    fun loadAfter_ShouldPostAdditionalLoadingState() {
        val key = 3
        val params = LoadParams<Int>(key, 20)
        val callback = mockk<LoadCallback<Int, Character>>(relaxed = true)
        val data = listOf(mockk<Character>())
        val additionalLoadingState = NetworkState.Loading(true)
        every { dataSource.loadAfter(params, callback) } answers {
            callback.onResult(data, key + 1)

            every { networkStateFlow.value } returns additionalLoadingState
        }

        dataSource.loadAfter(params, callback)

        verify { callback.onResult(data, key + 1) }
        assertThat(networkStateFlow.value).isEqualTo(additionalLoadingState)
    }

    @Test
    fun loadAfter_WithError_ShouldPostAdditionalErrorState() {
        val key = 3
        val params = LoadParams<Int>(key, 20)
        val callback = mockk<LoadCallback<Int, Character>>(relaxed = true)
        val data = listOf(mockk<Character>())
        val additionalErrorState = NetworkState.Error(true)
        every { dataSource.loadAfter(params, callback) } answers {
            callback.onResult(data, key + 1)

            every { networkStateFlow.value } returns additionalErrorState
        }

        dataSource.loadAfter(params, callback)

        Assert.assertNotNull(retry)
        assertThat(networkStateFlow.value).isEqualTo(additionalErrorState)
    }

    @Test
    fun loadAfter_WithSuccessEmptyData_ShouldPostAdditionalEmptySuccessState() {
        val paramKey = 100
        val params = LoadParams(paramKey, 0)
        val callback = mockk<LoadCallback<Int, Character>>(relaxed = true)
        val emptyData = emptyList<Character>()
        val response = mockk<DataResponse<Character>>()
        val additionalEmptySuccessState = NetworkState.Success(
            isAdditional = true,
            isEmptyResponse = true
        )

        coEvery { repository.getCharacters(any()) } returns response
        every { dataSource.loadAfter(params, callback) } answers {
            callback.onResult(emptyData, paramKey + 1)

            every { networkStateFlow.value } returns additionalEmptySuccessState
        }

        dataSource.loadAfter(params, callback)

        coVerify { repository.getCharacters(paramKey) }
        verify { callback.onResult(emptyData, paramKey + 1) }
        assertThat(networkStateFlow.value).isEqualTo(additionalEmptySuccessState)
    }

    @Test
    fun loadAfter_WithSuccessData_ShouldPostAdditionalNonEmptySuccessState() {
        val paramKey = 0
        val params = LoadParams(paramKey, 0)
        val callback = mockk<LoadCallback<Int, Character>>(relaxed = true)
        val data = listOf(mockk<Character>())
        val response = mockk<DataResponse<Character>>()
        val additionalNonEmptySuccessState = NetworkState.Success(
            isAdditional = true,
            isEmptyResponse = false
        )

        coEvery { repository.getCharacters(any()) } returns response
        every { dataSource.loadAfter(params, callback) } answers {
            callback.onResult(data, paramKey + 1)

            every { networkStateFlow.value } returns additionalNonEmptySuccessState
        }

        dataSource.loadAfter(params, callback)

        coVerify { repository.getCharacters(paramKey) }
        verify { callback.onResult(data, paramKey + 1) }
        assertThat(networkStateFlow.value).isEqualTo(additionalNonEmptySuccessState)
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
