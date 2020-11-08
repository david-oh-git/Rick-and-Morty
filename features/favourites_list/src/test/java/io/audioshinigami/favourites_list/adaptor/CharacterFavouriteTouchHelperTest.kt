/*
 * MIT License
 *
 * Copyright (c) 8/11/2020 13:15   David Osemwota.
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

package io.audioshinigami.favourites_list.adaptor

import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.google.common.truth.Truth.assertThat
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class CharacterFavouriteTouchHelperTest {

    @MockK(relaxed = true)
    lateinit var onSwiped: (Int) -> Unit
    lateinit var touchHelper: CharacterFavouriteTouchHelper

    @BeforeEach
    fun init(){
        MockKAnnotations.init(this)

        touchHelper = CharacterFavouriteTouchHelper(onSwiped)
    }

    @Test
    fun createHelper_confirmCreated() {
        val recyclerView = mockk<RecyclerView>()
        val viewHolder = mockk<RecyclerView.ViewHolder>()

        touchHelper.getDragDirs(recyclerView, viewHolder).run {
            assertThat(ACTION_STATE_IDLE).isEqualTo(this)
        }

        touchHelper.getSwipeDirs(recyclerView, viewHolder).run {
            assertThat(LEFT or RIGHT).isEqualTo(this)
        }
    }

    @Test
    fun moveTouchEvent_confirmIgnored() {
        val recycleView = mockk<RecyclerView>()
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val target = mockk<RecyclerView.ViewHolder>()

        touchHelper.onMove(recycleView, viewHolder, target).run {
            assertThat(this).isTrue()
        }

        verifyAll {
            recycleView wasNot Called
            viewHolder wasNot Called
            target wasNot Called
        }
        
    }

    @Test
    fun swipeTouchOnRemovedItem_confirmInvokeNoPosition(){
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val direction = LEFT
        val swipePosition = 5

        every { viewHolder.adapterPosition } returns swipePosition

        touchHelper.onSwiped(viewHolder, direction)

        verify { onSwiped.invoke(swipePosition) }
    }

    @Test
    fun swipeTouchEventItem_confirmInvokeWithPosition(){
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val direction = LEFT
        val swipePosition = 7

        every { viewHolder.adapterPosition } returns swipePosition

        touchHelper.onSwiped(viewHolder, direction)

        verify {
            onSwiped.invoke(swipePosition)
        }
    }
}