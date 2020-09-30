package io.audioshinigami.characters_list.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.same
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import javax.inject.Provider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersPageDataSourceFactoryTest {

    // TODO conclude tests

    /**
     * Executes tasks synchronously using Architecture components.
     */
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var providerDataSource: Provider<CharactersPageDataSource>

    @Spy
    lateinit var sourceLiveData: MutableLiveData<CharactersPageDataSource>
    @InjectMocks
    lateinit var dataSourceFactory: CharactersPageDataSourceFactory

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun initializeFactory_WithoutCreate_ShouldNotHaveDataSource() {
        verify(dataSourceFactory.sourceLiveData, never())
        Assert.assertNull(dataSourceFactory.sourceLiveData.value)
    }

    @Test
    fun initializeFactory_WithCreate_ShouldHaveDataSource() {
        doReturn(
            CharactersPageDataSource(mock(), mock())
        ).whenever(providerDataSource).get()

        val dataSource = dataSourceFactory.create() as CharactersPageDataSource

        verify(dataSourceFactory.sourceLiveData).postValue(same(dataSource))
    }

    @Test
    fun refreshDataSource_ShouldInvalidateData() {
        val dataSource = mock<CharactersPageDataSource>()
        doReturn(dataSource).whenever(sourceLiveData).value

        dataSourceFactory.refresh()

        verify(dataSource).invalidate()
        verify(dataSource, never()).retry()
    }

    @Test
    fun retryDataSource_ShouldRetryData() {
        val dataSource = mock<CharactersPageDataSource>()
        doReturn(dataSource).whenever(sourceLiveData).value

        dataSourceFactory.retry()

        verify(dataSource).retry()
        verify(dataSource, never()).invalidate()
    }
}
