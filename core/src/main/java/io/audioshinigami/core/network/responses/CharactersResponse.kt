package io.audioshinigami.core.network.responses

import io.audioshinigami.core.network.responses.characters.Character

/**
 * Rick and Morty Api response for characters
 *
 * @param info Details about the response
 * @param results List of [Character]s from API.
 */
data class CharactersResponse(
    val info: Info,
    val results: List<Character>
)