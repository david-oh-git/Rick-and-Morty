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
package io.audioshinigami.core.data.source

import io.audioshinigami.core.data.CharacterFavourite
import kotlinx.coroutines.flow.Flow

/**
 *  Entry point for the [CharacterFavourite] table in the
 *  database.
 */
interface CharacterFavouriteDataSource {

    /**
     *  Save characterFavourite object to the database.
     */
    suspend fun save(characterFavourite: CharacterFavourite)

    /**
     *  Get a list of all [CharacterFavourite] objects as a flow.
     */
    fun getAllCharacterFlow(): Flow<List<CharacterFavourite>>

    /**
     *  Get a list of all [CharacterFavourite] objects.
     */
    suspend fun getAllCharacters(): List<CharacterFavourite>

    /**
     * Delete all [CharacterFavourite] objects.
     */
    suspend fun deleteAllCharacters()

    /**
     * Delete [CharacterFavourite] item by id.
     */
    suspend fun deleteCharacterFavourite(id: Long)

    /**
     * Search for [CharacterFavourite] in database.
     *@param name of the [CharacterFavourite] item.
     * @return True if found in database or False otherwise.
     */
    suspend fun search(name: String): Boolean
}
