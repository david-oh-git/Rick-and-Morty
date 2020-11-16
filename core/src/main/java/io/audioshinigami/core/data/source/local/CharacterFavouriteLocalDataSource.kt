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

package io.audioshinigami.core.data.source.local

import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.core.data.source.CharacterFavouriteDataSource
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Implementation of [CharacterFavouriteDataSource].
 */
class CharacterFavouriteLocalDataSource @Inject constructor(
    private val characterFavouriteDao: CharacterFavouriteDao,
    private val ioDispatcher: CoroutineDispatcher
) : CharacterFavouriteDataSource {

    /**
     *  Save characterFavourite object to the database.
     */
    override suspend fun save(characterFavourite: CharacterFavourite) = withContext(ioDispatcher) {
        characterFavouriteDao.save(characterFavourite)
    }

    /**
     *  Get a list of all [CharacterFavourite] objects as a flow.
     */
    override fun getAllCharacterFlow(): Flow<List<CharacterFavourite>> {
        return characterFavouriteDao.getAllCharactersFlow()
    }

    /**
     *  Get a list of all [CharacterFavourite] objects.
     */
    override suspend fun getAllCharacters(): List<CharacterFavourite> = withContext(ioDispatcher) {
        return@withContext characterFavouriteDao.getAllCharacters()
    }

    /**
     * Delete all [CharacterFavourite] objects.
     */
    override suspend fun deleteAllCharacters() = withContext(ioDispatcher) {
        characterFavouriteDao.deleteAllCharacters()
    }

    override suspend fun deleteCharacterFavourite(id: Long) = withContext(ioDispatcher) {
        characterFavouriteDao.deleteCharacterFavourite(id)
    }

    override suspend fun search(name: String) = withContext(ioDispatcher) {
        val result = characterFavouriteDao.search(name)
        return@withContext result != null
    }
}
