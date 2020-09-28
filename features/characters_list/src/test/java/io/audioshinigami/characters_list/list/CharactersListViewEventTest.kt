package io.audioshinigami.characters_list.list

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class CharactersListViewEventTest {

    lateinit var event: CharactersListViewEvent

    @Test
    fun setEventOpenCharacterDetail_ShouldSettledCorrectly() {
        val characterId = 1
        event = CharactersListViewEvent.OpenCharacterDetail(characterId)

        val eventOpenDetail = event as CharactersListViewEvent.OpenCharacterDetail

        assertThat(characterId).isEqualTo(eventOpenDetail.id)
    }
}