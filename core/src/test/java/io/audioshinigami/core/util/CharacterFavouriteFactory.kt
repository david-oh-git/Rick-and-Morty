/*
 * MIT License
 *
 * Copyright (c) 2020 David Osemwota.
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

package io.audioshinigami.core.util

import io.audioshinigami.core.data.CharacterFavourite

/**
 * Factory object to create [CharacterFavourite] objects
 * for testing.
 */
object CharacterFavouriteFactory {

    fun getCharacter(): CharacterFavourite {
        val id = 140L
        val name = "Alhaji Rickmud Sanchez"
        val status = "Filthy Rich"
        val species = "Human"
        val type = "unknown"
        val gender = "male"
        val originName = "Gembu Taraba Earth R45"
        val locationName = "unknown"
        val image = "http://fakeurl.com/rickmud.jpg"
        val created = "2016-06-19"

        return CharacterFavourite(
            name = name,
            created = created,
            gender = gender,
            id = id,
            image = image,
            locationName = locationName,
            originName = originName,
            species = species,
            status = status,
            type = type
        )
    }
}