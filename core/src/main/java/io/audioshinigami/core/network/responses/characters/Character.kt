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

package io.audioshinigami.core.network.responses.characters

/**
 *  Represents a Rick and Morty character from API
 *
 *  @param id Unique id of the character.
 *  @param name The name of the character
 *  @param status The status if the character is DEAD or ALIVE or UNKNOWN
 *  @param species The character's species
 *  @param type The type or sub species of the character
 *  @param gender The Character's gender
 *  @param origin [BaseLocation] with [BaseLocation.name] representing origin's name
 *  and [BaseLocation.url] url to the location
 *  @param location [BaseLocation] with [BaseLocation.name] representing character's
 *  planet name and [BaseLocation.url] url to the location
 *  @param image Link to the Character's image, 300px x 300px.
 *  @param episode List of episodes the character featured in.
 *  @param url The URL to the character
 *  @param created Date the character was created in API database.
 */
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: BaseLocation,
    val location: BaseLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
