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

package io.audioshinigami.characters_list.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.same
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Provider

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
    lateinit var sourceFlow: MutableStateFlow<CharactersPageDataSource?>

    @InjectMocks
    lateinit var dataSourceFactory: CharactersPageDataSourceFactory

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun reset(){
//        unmockkAll()
//        validateMockitoUsage()
    }

    @Test
    fun initializeFactory_WithoutCreate_ShouldNotHaveDataSource() {

        verify(dataSourceFactory.sourceFlow, never())
        Assert.assertNull(dataSourceFactory.sourceFlow.value)
    }

    @Test
    fun initializeFactory_WithCreate_ShouldHaveDataSource() {
        doReturn(
            CharactersPageDataSource(mock(), mock())
        ).whenever(providerDataSource).get()

        val dataSource = dataSourceFactory.create() as CharactersPageDataSource

        verify(dataSourceFactory.sourceFlow).value = same(dataSource)
    }

    @Test
    fun refreshDataSource_ShouldInvalidateData() {
        val dataSource = mock<CharactersPageDataSource>()
        doReturn(dataSource).whenever(sourceFlow).value

        dataSourceFactory.refresh()

        assertThat(dataSourceFactory.sourceFlow.value).isEqualTo(dataSource)

        verify(dataSource).invalidate()
//        verify(dataSource, never()).retry()
    }

    @Test
    fun retryDataSource_ShouldRetryData() {
        val dataSource = mock<CharactersPageDataSource>()
        doReturn(dataSource).whenever(sourceFlow).value

        dataSourceFactory.retry()

        verify(dataSource).retry()
//        verify(dataSource, never()).invalidate()

    }
}
