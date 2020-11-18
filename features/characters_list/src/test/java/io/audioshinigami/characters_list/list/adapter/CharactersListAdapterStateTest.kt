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
package io.audioshinigami.characters_list.list.adapter

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CharactersListAdapterStateTest {

    lateinit var state: CharactersListAdapterState

    @Test
    @DisplayName("Set state to Added , then confirm result is as.")
    fun setStateAdded_Confirm() {
        // Act: set state value to Added.
        state = CharactersListAdapterState.Added

        // Assert: confirm state is Added along with properties.
        assertThat(state.hasExtraRow).isTrue()
        assertThat(state.isAdded()).isTrue()

        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isNoMore()).isFalse()
    }

    @Test
    @DisplayName("Set state to AddLoading , then confirm result is as.")
    fun setStateAddLoading_Confirm() {
        // Act: set state value to Added.
        state = CharactersListAdapterState.AddLoading

        // Assert: confirm state is Added along with properties.
        assertThat(state.hasExtraRow).isTrue()
        assertThat(state.isAddLoading()).isTrue()

        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAdded()).isFalse()
        assertThat(state.isNoMore()).isFalse()
    }

    @Test
    @DisplayName("Set state to AddError , then confirm result is as.")
    fun setStateAddError_Confirm() {
        // Act: set state value to Added.
        state = CharactersListAdapterState.AddError

        // Assert: confirm state is Added along with properties.
        assertThat(state.hasExtraRow).isTrue()
        assertThat(state.isAddError()).isTrue()

        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isAdded()).isFalse()
        assertThat(state.isNoMore()).isFalse()
    }

    @Test
    @DisplayName("Set state to NoMore , then confirm result is as.")
    fun setStateNoMore_Confirm() {
        // Act: set state value to Added.
        state = CharactersListAdapterState.NoMore

        // Assert: confirm state is Added along with properties.
        assertThat(state.hasExtraRow).isFalse()
        assertThat(state.isNoMore()).isTrue()

        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAdded()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
    }
}
