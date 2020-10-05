package io.audioshinigami.characters_list.detail.models

import io.audioshinigami.core.mapper.Mapper
import io.audioshinigami.core.network.responses.characters.Character

/**
 * Helper class to transform [Character] input to [CharacterDetail] output.
 */
class CharacterDetailMapper: Mapper<Character, CharacterDetail> {

    /**
     * Transforms input to desired output.
     *
     * @param from A character.
     * @return A characterDetail(parceble version) class
     */
    override suspend fun transform(from: Character): CharacterDetail {
        return CharacterDetail(
            name = from.name,
            type = from.type,
            gender = from.gender,
            species = from.species,
            status = from.status,
            characterUrl = from.url,
            created = from.created,
            episode = from.episode,
            image = from.image,
            locationName = from.location.name,
            locationUrl = from.location.url,
            originName = from.origin.name,
            originUrl = from.origin.url
        )
    }
}