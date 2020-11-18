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
