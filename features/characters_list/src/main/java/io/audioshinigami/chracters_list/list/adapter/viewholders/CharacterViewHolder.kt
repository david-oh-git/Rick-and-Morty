package io.audioshinigami.chracters_list.list.adapter.viewholders

import android.view.LayoutInflater
import io.audioshinigami.chracters_list.databinding.ListItemCharacterBinding
import io.audioshinigami.chracters_list.list.CharactersListViewModel
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.ui.base.BaseViewHolder

/**
 * View holder for RecyclerView item with [Character] data.
 */
class CharacterViewHolder(
    inflater: LayoutInflater
):  BaseViewHolder<ListItemCharacterBinding>(
    ListItemCharacterBinding.inflate(inflater)
) {

    /**
     * Bind data variables for view holder.
     */
    fun bind(_viewModel: CharactersListViewModel, _character: Character){
        binding.apply {
            viewModel = _viewModel
            character = _character
            executePendingBindings()
        }
    }
}