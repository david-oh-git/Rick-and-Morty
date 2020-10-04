package io.audioshinigami.characters_list.list

import io.audioshinigami.core.network.responses.characters.Character

/**
 *  Interaction events for [CharactersListFragment].
 */
sealed class CharactersListViewEvent {

    /**
     *  Open Character detail view.
     *
     *  @param id Unique to character.
     */
    data class OpenCharacterDetail(val character: Character) : CharactersListViewEvent()
}
