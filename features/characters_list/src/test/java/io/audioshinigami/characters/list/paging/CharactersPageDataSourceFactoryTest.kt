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
package io.audioshinigami.characters.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import javax.inject.Provider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
internal class CharactersPageDataSourceFactoryTest {

    /**
     * Executes tasks synchronously using Architecture components.
     */
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var providerDataSource: Provider<CharactersPageDataSource>

    @SpyK
    var sourceFlow: MutableStateFlow<CharactersPageDataSource?> = MutableStateFlow(null)

    private lateinit var dataSourceFactory: CharactersPageDataSourceFactory

    @BeforeEach
    fun init() {
        dataSourceFactory = CharactersPageDataSourceFactory(providerDataSource)
        dataSourceFactory.sourceFlow = sourceFlow
    }

    @Test
    fun initializeFactory_WithoutCreate_ShouldNotHaveDataSource() {

        verify(inverse = true) { dataSourceFactory.sourceFlow.value }
        assertThat(dataSourceFactory.sourceFlow.value).isNull()
    }

    @Test
    fun initializeFactory_WithCreate_ShouldHaveDataSource() {
        // Arrange: stub providerDataSource get method.
        every { providerDataSource.get() } returns CharactersPageDataSource(mockk(), mockk())

        val dataSource = dataSourceFactory.create() as CharactersPageDataSource

        assertThat(dataSource).isEqualTo(dataSourceFactory.sourceFlow.value)
    }

    @Test
    fun refreshDataSource_ShouldInvalidateData() {
        val dataSource = mockk<CharactersPageDataSource>(relaxed = true)
        every { sourceFlow.value } returns dataSource

        dataSourceFactory.refresh()

        assertThat(dataSourceFactory.sourceFlow.value).isEqualTo(dataSource)

        verify { dataSource.invalidate() }
        verify(inverse = true) { dataSource.retry() }
    }

    @Test
    fun retryDataSource_ShouldRetryData() {
        val dataSource = mockk<CharactersPageDataSource>(relaxed = true)
        every { sourceFlow.value } returns dataSource

        dataSourceFactory.retry()

        verify { dataSource.retry() }
        verify(inverse = true) { dataSource.invalidate() }
    }
}
