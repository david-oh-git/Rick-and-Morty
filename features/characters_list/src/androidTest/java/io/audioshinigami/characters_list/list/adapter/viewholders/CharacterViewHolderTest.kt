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

package io.audioshinigami.characters_list.list.adapter.viewholders

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.characters_list.databinding.ListItemCharacterBinding
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
class CharacterViewHolderTest {

    // TODO  fix issue as test wont run
    @MockK
    lateinit var view: View
    @MockK
    lateinit var layoutInflater: LayoutInflater
    @MockK(relaxed = true)
    lateinit var binding: ListItemCharacterBinding
    lateinit var viewHolder: CharacterViewHolder

    @Before
    fun init(){
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly(){
        mockkStatic(ListItemCharacterBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemCharacterBinding.inflate(layoutInflater) } returns binding

        viewHolder = CharacterViewHolder(layoutInflater)

        assertThat(viewHolder.binding).isEqualTo(binding)
    }
}