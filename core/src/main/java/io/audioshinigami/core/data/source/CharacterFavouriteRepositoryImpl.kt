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
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CharacterFavouriteRepositoryImpl @Inject constructor(
    private val localDataSource: CharacterFavouriteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : CharacterFavouriteRepository {

    override suspend fun save(characterFavourite: CharacterFavourite) = withContext(ioDispatcher) {
        localDataSource.save(characterFavourite)
    }

    override fun getAllCharacterFlow(): Flow<List<CharacterFavourite>> {
        return localDataSource.getAllCharacterFlow()
    }

    override suspend fun getAllCharacters(): List<CharacterFavourite> = withContext(ioDispatcher) {
        return@withContext localDataSource.getAllCharacters()
    }

    override suspend fun deleteAllCharacters() = withContext(ioDispatcher) {
        localDataSource.deleteAllCharacters()
    }

    override suspend fun deleteCharacterFavourite(id: Long) = withContext(ioDispatcher) {
        localDataSource.deleteCharacterFavourite(id)
    }

    override suspend fun search(name: String): Boolean = withContext(ioDispatcher) {
        return@withContext localDataSource.search(name)
    }
}
