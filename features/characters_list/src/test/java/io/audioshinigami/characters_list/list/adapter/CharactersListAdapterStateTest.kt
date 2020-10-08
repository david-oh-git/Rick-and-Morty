package io.audioshinigami.characters_list.list.adapter

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CharactersListAdapterStateTest {

    lateinit var state: CharactersListAdapterState

    @Test
    @DisplayName("Set state to Added , then confirm result is as.")
    fun setStateAdded_Confirm(){
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
    fun setStateAddLoading_Confirm(){
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
    fun setStateAddError_Confirm(){
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
    fun setStateNoMore_Confirm(){
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