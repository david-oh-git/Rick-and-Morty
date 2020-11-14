/*
 * MIT License
 *
 * Copyright (c) 8/11/2020 17:21   David Osemwota.
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

package io.audioshinigami.favourites_list.adaptor.holders

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.favourites_list.databinding.ListItemFavouriteBinding
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CharacterFavouriteViewHolderTest {

    @MockK
    lateinit var view: View
    @MockK
    lateinit var layoutInflater: LayoutInflater
    @MockK(relaxed = true)
    lateinit var binding: ListItemFavouriteBinding
    lateinit var viewHolder: CharacterFavouriteViewHolder

    @BeforeEach
    fun init() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly() {
        // Arrange: mock & set return value for mocks
        mockkStatic(ListItemFavouriteBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemFavouriteBinding.inflate(layoutInflater) } returns binding

        // Act: mock vieHolder
        viewHolder = CharacterFavouriteViewHolder(layoutInflater)

        // Assert:
        assertThat(viewHolder.binding).isEqualTo(binding)
    }

    @Test
    fun bindViewHolder_shouldBindingDataVariable() {
        // Arrange: mock & set return value for mocks
        mockkStatic(ListItemFavouriteBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemFavouriteBinding.inflate(layoutInflater) } returns binding

        // Act: mock viewHolder and call bind.
        val characterFavourite = mockk<CharacterFavourite>()
        viewHolder = CharacterFavouriteViewHolder(layoutInflater)
        viewHolder.bind(characterFavourite)

        // Assert:
        verify { binding.characterFavourite = characterFavourite }
        verify { binding.executePendingBindings() }
    }
}
