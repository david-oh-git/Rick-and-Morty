package io.audioshinigami.characters_list.list

/**
 *  Interaction events for [CharactersListFragment].
 */
sealed class CharactersListViewEvent {

    /**
     *  Open Character detail view.
     *
     *  @param id Unique to character.
     */
    data class OpenCharacterDetail(val id: Int) : CharactersListViewEvent()
}
