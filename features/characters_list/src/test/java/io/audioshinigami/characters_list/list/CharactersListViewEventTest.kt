package io.audioshinigami.characters_list.list

import com.google.common.truth.Truth.assertThat
import io.audioshinigami.core.network.responses.characters.BaseLocation
import io.audioshinigami.core.network.responses.characters.Character
import org.junit.jupiter.api.Test

internal class CharactersListViewEventTest {

    lateinit var event: CharactersListViewEvent

    @Test
    fun setEventOpenCharacterDetail_ShouldSettledCorrectly() {
        val character = Character(
            name = "Summer",
            type = "lute",
            gender = "Female",
            species = "Human",
            status = "null",
            created = "18 of July",
            episode = listOf("fake episode name"),
            image = "http://upload.com/summer.jpg",
            id = 45,
            location = BaseLocation("Earth Bi15", "http'//"),
            origin = BaseLocation("Earth Bi15", "http'//"),
            url = "http://"
        )
        event = CharactersListViewEvent.OpenCharacterDetail(character)

        val eventOpenDetail = event as CharactersListViewEvent.OpenCharacterDetail

        assertThat(character).isEqualTo(eventOpenDetail.character)
    }
}
