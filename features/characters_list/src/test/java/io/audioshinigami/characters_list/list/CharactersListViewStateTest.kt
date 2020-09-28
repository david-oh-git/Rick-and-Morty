package io.audioshinigami.characters_list.list

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class CharactersListViewStateTest {

    lateinit var state: CharactersListViewState

    @Test
    fun setStateAsRefreshing_ShouldBeSettled(){
        state = CharactersListViewState.Refreshing

        assertThat(state.isRefreshing()).isTrue()
        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isNoMoreElements()).isFalse()
        assertThat(state.isError()).isFalse()
        assertThat(state.isLoaded()).isFalse()
        assertThat(state.isLoading()).isFalse()
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled(){
        state = CharactersListViewState.AddError

        assertThat(state.isAddError()).isTrue()
        assertThat(state.isRefreshing()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isNoMoreElements()).isFalse()
        assertThat(state.isError()).isFalse()
        assertThat(state.isLoaded()).isFalse()
        assertThat(state.isLoading()).isFalse()
    }

    @Test
    fun setStateAddLoading_ShouldBeSettled(){
        state = CharactersListViewState.AddLoading

        assertThat(state.isRefreshing()).isFalse()
        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isTrue()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isNoMoreElements()).isFalse()
        assertThat(state.isError()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isLoaded()).isFalse()
        assertThat(state.isLoading()).isFalse()
    }

    @Test
    fun setStateAsEmpty_ShouldBeSettled(){
        state = CharactersListViewState.Empty

        assertThat(state.isRefreshing()).isFalse()
        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isEmpty()).isTrue()
        assertThat(state.isNoMoreElements()).isFalse()
        assertThat(state.isError()).isFalse()
        assertThat(state.isLoaded()).isFalse()
        assertThat(state.isLoading()).isFalse()
    }

    @Test
    fun setStateAsNoMoreElements_ShouldBeSettled(){
        state = CharactersListViewState.NoMoreElements

        assertThat(state.isRefreshing()).isFalse()
        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isNoMoreElements()).isTrue()
        assertThat(state.isError()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isLoaded()).isFalse()
        assertThat(state.isLoading()).isFalse()
    }

    @Test
    fun setStateAsError_ShouldBeSettled(){
        state = CharactersListViewState.Error

        assertThat(state.isRefreshing()).isFalse()
        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isNoMoreElements()).isFalse()
        assertThat(state.isError()).isTrue()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isLoaded()).isFalse()
        assertThat(state.isLoading()).isFalse()
    }

    @Test
    fun setStateAsLoaded_ShouldBeSettled(){
        state = CharactersListViewState.Loaded

        assertThat(state.isRefreshing()).isFalse()
        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isNoMoreElements()).isFalse()
        assertThat(state.isError()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isLoaded()).isTrue()
        assertThat(state.isLoading()).isFalse()
    }

    @Test
    fun setStateAsLoading_ShouldBeSettled(){
        state = CharactersListViewState.Loading

        assertThat(state.isRefreshing()).isFalse()
        assertThat(state.isAddError()).isFalse()
        assertThat(state.isAddLoading()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isNoMoreElements()).isFalse()
        assertThat(state.isError()).isFalse()
        assertThat(state.isEmpty()).isFalse()
        assertThat(state.isLoaded()).isFalse()
        assertThat(state.isLoading()).isTrue()
    }
}