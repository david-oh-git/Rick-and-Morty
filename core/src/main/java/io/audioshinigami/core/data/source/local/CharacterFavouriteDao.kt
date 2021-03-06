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
package io.audioshinigami.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.core.utils.CHARACTER_TABLE
import kotlinx.coroutines.flow.Flow

/**
 * Room dao access object for [CharacterFavourite] class.
 */
@Dao
interface CharacterFavouriteDao {

    /**
     * Add a [CharacterFavourite] to database.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(characterFavourite: CharacterFavourite)

    /**
     * Get all [CharacterFavourite] from the database via
     * kotlin flow.
     */
    @Query("SELECT * FROM $CHARACTER_TABLE ORDER BY id")
    fun getAllCharactersFlow(): Flow<List<CharacterFavourite>>

    /**
     * Get a list of all [CharacterFavourite] from the database.
     */
    @Query("SELECT * FROM $CHARACTER_TABLE ORDER BY id")
    suspend fun getAllCharacters(): List<CharacterFavourite>

    /**
     * Delete all [CharacterFavourite] from the database.
     */
    @Query("DELETE FROM $CHARACTER_TABLE")
    suspend fun deleteAllCharacters()

    @Query("DELETE FROM $CHARACTER_TABLE WHERE id = :id")
    suspend fun deleteCharacterFavourite(id: Long)

    /**
     * Searches database for [CharacterFavourite] with the name,
     * name is unique so ideally there should be one or none.
     * @param name Name of the [CharacterFavourite] item .
     * @return The item with the name if found or null otherwise.
     */
    @Query("SELECT * FROM $CHARACTER_TABLE WHERE name = :name")
    suspend fun search(name: String): CharacterFavourite?
}
