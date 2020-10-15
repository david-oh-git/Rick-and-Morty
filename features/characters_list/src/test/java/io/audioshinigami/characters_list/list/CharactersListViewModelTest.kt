/*
 * MIT License
 *
 * Copyright (c) 2020 David Osemwota.
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

package io.audioshinigami.characters_list.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.characters_list.list.paging.CharactersPageDataSource
import io.audioshinigami.characters_list.list.paging.CharactersPageDataSourceFactory
import io.audioshinigami.core.network.NetworkState
import io.audioshinigami.core.network.responses.characters.BaseLocation
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.test_utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CharactersListViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTarget = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var dataSourceFactory: CharactersPageDataSourceFactory
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<CharactersListViewState>
    @MockK(relaxed = true)
    lateinit var eventObserver: Observer<CharactersListViewEvent>
    lateinit var viewModel: CharactersListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun networkSuccessEmptyCharacters_ShouldBeEmptyState() {
        val networkState = NetworkState.Success(
            isEmptyResponse = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Empty
        assertThat(expectedState).isEqualTo(viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalEmptyCharacters_ShouldBeNoMoreElementsState() {
        val networkState = NetworkState.Success(
            isEmptyResponse = true,
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.NoMoreElements
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loaded
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loaded
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkLoadingCharacters_ShouldBeLoadingState() {
        val networkState = NetworkState.Loading()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loading
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalLoadingCharacters_ShouldBeAddLoadingState() {
        val networkState = NetworkState.Loading(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.AddLoading
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkErrorCharacters_ShouldBeErrorState() {
        val networkState = NetworkState.Error()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Error
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalErrorCharacters_ShouldBeAddErrorState() {
        val networkState = NetworkState.Error(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.AddError
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun refreshCharacterList_ShouldInvokeDataSourceMethod() {

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)

        viewModel.refreshLoadedCharacterList()

        verify { dataSourceFactory.refresh() }
        verify(exactly = 0) { dataSourceFactory.retry() }
    }

    @Test
    fun retryCharacterList_ShouldInvokeDataSourceMethod() {
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.retryAddCharactersList()

        verify { dataSourceFactory.retry() }
        verify(exactly = 0) { dataSourceFactory.refresh() }
    }

    @Test
    fun openCharacterDetail_ShouldSendAsEvent() {

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.event.observeForever(eventObserver)

        val character = Character(
            name = "Summer",
            type = "lute",
            gender = "Female",
            species = "Human",
            status = "null",
            created = "18 of July",
            episode = listOf("fake episode name"),
            image = "http://upload.com/summer.jpg",
            id = 45,
            location = BaseLocation("Earth Bi15", "http'//"),
            origin = BaseLocation("Earth Bi15", "http'//"),
            url = "http://"
        )
        viewModel.openCharacterDetail(character)

        val expectedEvent = CharactersListViewEvent.OpenCharacterDetail(character)
        assertThat(expectedEvent).isEqualTo(viewModel.event.value)
        verify { eventObserver.onChanged(expectedEvent) }
    }

    class FakeCharactersPageDataSource(
        forceNetworkState: NetworkState
    ) : CharactersPageDataSource(
        repository = mockk(),
        scope = mockk()
    ) {
        init {
            networkState.postValue(forceNetworkState)
        }
    }
}
