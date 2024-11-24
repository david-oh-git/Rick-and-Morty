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
package io.audioshinigami.characters.detail.models

import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.core.mapper.Mapper

/**
 * Helper to transform [CharacterDetail] input to [CharacterFavourite] output.
 */
class CharacterFavouriteMapper : Mapper<CharacterDetail, CharacterFavourite> {

    /**
     * Transforms input to output.
     *
     * @param from input [CharacterDetail] class.
     * @return The desired output [CharacterFavourite].
     */
    override suspend fun transform(from: CharacterDetail): CharacterFavourite {
        return CharacterFavourite(
            name = from.name,
            created = from.created,
            gender = from.gender,
            id = 0,
            image = from.image,
            locationName = from.locationName,
            originName = from.originName,
            species = from.species,
            status = from.status,
            type = from.type
        )
    }
}
