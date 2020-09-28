package io.audioshinigami.characters_list.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.characters_list.list.paging.CharactersPageDataSource
import io.audioshinigami.characters_list.list.paging.CharactersPageDataSourceFactory
import io.audioshinigami.core.network.NetworkState
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
    var corotinesTestRule = MainCoroutineRule()

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
    fun networkSuccessAdditionalEmptyCharacters_ShouldBeNoMoreElementsState(){
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
    fun networkSuccessAdditionalCharacters_ShouldBeLoadedState(){
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
    fun networkSuccessCharacters_ShouldBeLoadedState(){
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
    fun networkLoadingCharacters_ShouldBeLoadingState(){
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
    fun networkAdditionalLoadingCharacters_ShouldBeAddLoadingState(){
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
    fun networkErrorCharacters_ShouldBeErrorState(){
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
    fun networkAdditionalErrorCharacters_ShouldBeAddErrorState(){
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
    fun refreshCharacterList_ShouldInvokeDataSourceMethod(){

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)

        viewModel.refreshLoadedCharacterList()


        verify { dataSourceFactory.refresh() }
        verify(exactly = 0) { dataSourceFactory.retry() }

    }

    @Test
    fun retryCharacterList_ShouldInvokeDataSourceMethod(){
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.retryAddCharactersList()

        verify { dataSourceFactory.retry() }
        verify(exactly = 0) { dataSourceFactory.refresh() }
    }

    @Test
    fun openCharacterDetail_ShouldSendAsEvent(){

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.event.observeForever(eventObserver)

        val characterid = 3
        viewModel.openCharacterDetail(characterid)

        val expectedEvent = CharactersListViewEvent.OpenCharacterDetail(characterid)
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