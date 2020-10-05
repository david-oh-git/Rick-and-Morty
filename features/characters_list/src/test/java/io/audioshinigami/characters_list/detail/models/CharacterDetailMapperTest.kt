package io.audioshinigami.characters_list.detail.models

import com.google.common.truth.Truth.assertThat
import io.audioshinigami.core.network.responses.characters.BaseLocation
import io.audioshinigami.core.network.responses.characters.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class CharacterDetailMapperTest {

    private val mapper = CharacterDetailMapper()

    @Test
    @DisplayName("Confirm mapper transforms character object correctly to characterDetail")
    fun mapperShouldTransformCharacterToCharacterDetail() = runBlockingTest {
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

        mapper.transform(character).run {
            assertThat(character.name).isEqualTo(this.name)
            assertThat(character.type).isEqualTo(this.type)
            assertThat(character.gender).isEqualTo(this.gender)
            assertThat(character.species).isEqualTo(this.species)
            assertThat(character.status).isEqualTo(this.status)
            assertThat(character.created).isEqualTo(this.created)
            assertThat(character.episode).isEqualTo(this.episode)
            assertThat(character.location.name).isEqualTo(this.locationName)
            assertThat(character.origin.name).isEqualTo(this.originName)
        }
    }
}