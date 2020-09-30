package io.audioshinigami.core.network.responses.characters

import io.audioshinigami.core.network.responses.EndPoint

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
 *  @param episode [EndPoint] list of episodes the character featured in.
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
