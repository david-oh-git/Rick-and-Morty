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
package io.audioshinigami.favourites

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class FavouriteListViewStateTest {

    lateinit var state: FavouriteListViewState

    @Test
    fun setStateEmpty_confirmStateIsEmpty() {
        state = FavouriteListViewState.Empty

        assertThat(state.isEmpty()).isTrue()
        assertThat(state.isListed()).isFalse()
        assertThat(state.isError()).isFalse()
    }

    @Test
    fun setStateListed_confirmStateIsListed() {
        state = FavouriteListViewState.Listed

        assertThat(state.isListed()).isTrue()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isError()).isFalse()
    }

    @Test
    fun setStateError_confirmStateIsError() {
        state = FavouriteListViewState.Error

        assertThat(state.isError()).isTrue()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isListed()).isFalse()
    }
}
