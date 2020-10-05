package io.audioshinigami.characters_list.detail.models

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CharacterDetailTest {

    @Test
    @DisplayName("When a CharacterDetail class is created, its attributes should be correct")
    fun createCharacterDetail_shouldAddCorrectAttributes(){
        val name = "Alhaji Rickmud Sanchez"
        val status = "Filthy Rich"
        val species = "Human"
        val type = "unknown"
        val gender = "male"
        val originName = "Gembu Taraba Earth R45"
        val originUrl = "http://"
        val locationName = "unknown"
        val locationUrl = "http://fakeurl.com/location/rickmud_current"
        val image = "http://fakeurl.com/rickmud.jpg"
        val episode: List<String> = listOf("Chronicles of sugar cane")
        val characterUrl = "http://"
        val created = "2016-06-19"

        val characterDetail = CharacterDetail(
            locationName = locationName,
            originUrl = originUrl,
            originName = originName,
            locationUrl = locationUrl,
            image = image,
            episode = episode,
            created = created,
            characterUrl = characterUrl,
            status = status,
            species = species,
            gender = gender,
            type = type,
            name = name
        )

        assertThat(name).isEqualTo(characterDetail.name)
        assertThat(status).isEqualTo(characterDetail.status)
        assertThat(species).isEqualTo(characterDetail.species)
        assertThat(type).isEqualTo(characterDetail.type)
        assertThat(gender).isEqualTo(characterDetail.gender)
        assertThat(originName).isEqualTo(characterDetail.originName)
        assertThat(originUrl).isEqualTo(characterDetail.originUrl)
        assertThat(locationName).isEqualTo(characterDetail.locationName)
        assertThat(locationUrl).isEqualTo(characterDetail.locationUrl)
        assertThat(image).isEqualTo(characterDetail.image)
        assertThat(episode).isEqualTo(characterDetail.episode)
        assertThat(episode.size).isEqualTo(characterDetail.episode.size)
        assertThat(characterUrl).isEqualTo(characterDetail.characterUrl)
        assertThat(created).isEqualTo(characterDetail.created)
    }
}