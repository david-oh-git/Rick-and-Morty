package io.audioshinigami.chracters_list.list.adapter.viewholders

import android.view.LayoutInflater
import io.audioshinigami.chracters_list.databinding.ListItemErrorBinding
import io.audioshinigami.chracters_list.list.CharactersListViewModel
import io.audioshinigami.ui.base.BaseViewHolder

/**
 * Error view holder.
 */
class ErrorViewHolder(
    inflater: LayoutInflater
): BaseViewHolder<ListItemErrorBinding>(
    ListItemErrorBinding.inflate(inflater)
) {

    /**
     *  Binding variables.
     */
    fun bind(_viewModel: CharactersListViewModel){
        binding.apply {
            viewModel = _viewModel
            executePendingBindings()
        }
    }
}