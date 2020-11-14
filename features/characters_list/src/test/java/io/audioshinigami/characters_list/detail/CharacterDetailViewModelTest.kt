/*
 * MIT License
 *
 * Copyright (c) 29/10/2020 14:25   David Osemwota.
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

package io.audioshinigami.characters_list.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.characters_list.detail.models.CharacterDetail
import io.audioshinigami.characters_list.detail.models.CharacterFavouriteMapper
import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.core.data.source.CharacterFavouriteRepository
import io.audioshinigami.test_utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CharacterDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var state: Observer<CharacterDetailViewState>
    @MockK(relaxed = true)
    lateinit var data: Observer<CharacterDetail>
    @MockK(relaxed = true)
    lateinit var repository: CharacterFavouriteRepository
    @MockK
    lateinit var characterFavouriteMapper: CharacterFavouriteMapper
    lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this)

        viewModel = CharacterDetailViewModel(
                repository = repository,
                characterFavouriteMapper
        )
        viewModel.state.observeForever(state)
        viewModel.data.observeForever(data)
    }

    @Test
    fun dismissCharacterDetail_ViewStateShouldBeDismiss() {
        val expectedState = CharacterDetailViewState.Dismiss

        viewModel.dismissCharacterDetail()

        assertThat(expectedState).isEqualTo(viewModel.state.value)
        verify { state.onChanged(expectedState) }
    }

    @Test
    fun addToFavouriteCharacterDetail_ViewStateShouldBeAddToFavourite() {
        val expectedState = CharacterDetailViewState.AddToFavorite
        val characterDetail = mockk<CharacterDetail>()

        viewModel.setData(characterDetail)

        assertThat(viewModel.data.value).isNotNull()
        assertThat(viewModel.data.value).isEqualTo(characterDetail)
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { state.onChanged(expectedState) }
    }

    @Test
    fun addedToFavouriteCharacterDetail_ViewStateShouldBeAddedToFavourite() {
        val expectedState = CharacterDetailViewState.AddedToFavorite
        val characterFavourite = mockk<CharacterFavourite>()
        val characterDetail = mockk<CharacterDetail>()
        coEvery { characterFavouriteMapper.transform(characterDetail) } returns characterFavourite

        viewModel.setData(characterDetail)
        viewModel.addCharacterToFavorite()

        assertThat(viewModel.data.value).isNotNull()
        assertThat(viewModel.state.value).isEqualTo(expectedState)
        verify { state.onChanged(expectedState) }
        coVerify { repository.save(characterFavourite) }
    }
}
