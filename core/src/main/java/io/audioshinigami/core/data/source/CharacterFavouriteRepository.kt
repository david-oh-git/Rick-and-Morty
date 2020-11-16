/*
 * MIT License
 *
 * Copyright (c) 21/10/2020 15:26   David Osemwota.
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

/**
 *  Repository for [CharacterFavourite] data operations on the database.
 */
interface CharacterFavouriteRepository {

    /**
     * Save characterFavourite to database.
     *
     * @param characterFavourite item to be saved.
     */
    suspend fun save(characterFavourite: CharacterFavourite)

    /**
     * Get all list of all [CharacterFavourite] items in the database as a
     * flow.
     */
    fun getAllCharacterFlow(): kotlinx.coroutines.flow.Flow<List<CharacterFavourite>>

    /**
     * Get all list of all [CharacterFavourite] items in the database.
     */
    suspend fun getAllCharacters(): List<CharacterFavourite>

    /**
     * Delete all list of all [CharacterFavourite] items in the database.
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
