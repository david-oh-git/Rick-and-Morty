package io.audioshinigami.characters_list.detail.models

import android.os.Parcelable
import io.audioshinigami.core.network.responses.characters.BaseLocation
import kotlinx.android.parcel.Parcelize

/**
 *  A [Parcelable] class with all of the character's details.
 */
@Parcelize
class CharacterDetail(
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originName: String,
    val originUrl: String,
    val locationName: String,
    val locationUrl: String,
    val image: String,
    val episode: List<String>,
    val characterUrl: String,
    val created: String
): Parcelable