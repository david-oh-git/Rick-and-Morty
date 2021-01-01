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
package io.audioshinigami.characters_list.list.adapter.viewholders

import android.view.LayoutInflater
import io.audioshinigami.characters_list.databinding.ListItemCharacterBinding
import io.audioshinigami.characters_list.list.CharactersListViewModel
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.ui.base.BaseViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * View holder for RecyclerView item with [Character] data.
 */
class CharacterViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharacterBinding>(
    ListItemCharacterBinding.inflate(inflater)
) {

    /**
     * Bind data variables for view holder.
     */
    @ExperimentalCoroutinesApi
    fun bind(_viewModel: CharactersListViewModel, _character: Character) {
        binding.apply {
            viewModel = _viewModel
            character = _character
            executePendingBindings()
        }
    }
}
