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
package io.audioshinigami.characters_list.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.audioshinigami.characters_list.list.CharactersListViewModel
import io.audioshinigami.characters_list.list.adapter.viewholders.CharacterViewHolder
import io.audioshinigami.characters_list.list.adapter.viewholders.ErrorViewHolder
import io.audioshinigami.characters_list.list.adapter.viewholders.LoadingViewHolder
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.ui.base.BasePagedListAdapter
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Enum class representing all types of RecyclerView item.
 */
internal enum class ItemView(val type: Int, val span: Int) {
    CHARACTER(type = 0, span = 1),
    LOADING(type = 1, span = 2),
    ERROR(type = 2, span = 2);

    companion object {
        fun valueOf(type: Int): ItemView = values().first { it.type == type }
    }
}

/**
 * Adaptor for characters List data in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 *
 */
@ExperimentalCoroutinesApi
class CharactersListAdapter @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val viewModel: CharactersListViewModel
) : BasePagedListAdapter<Character>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: CharactersListAdapterState = CharactersListAdapterState.Added

    /**
     * Creates a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param inflater Instantiates a layout XML file into its corresponding View objects.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (ItemView.valueOf(viewType)) {
            ItemView.CHARACTER -> CharacterViewHolder(inflater)
            ItemView.LOADING -> LoadingViewHolder(inflater)
            else -> ErrorViewHolder(inflater)
        }

    /**
     * Bind the data at the specified position in the recycclerView.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemView(position)) {
            ItemView.CHARACTER ->
                getItem(position)?.let {
                    if (holder is CharacterViewHolder) {
                        holder.bind(viewModel, it)
                    }
                }
            ItemView.ERROR ->
                if (holder is ErrorViewHolder) {
                    holder.bind(viewModel)
                }
            else -> {
            }
        }
    }

    /**
     * ID for the item at position.
     *
     * @param position Adapter position to query.
     * @return The stable ID of the item at position.
     * @see BasePagedListAdapter.getItemId
     */
    override fun getItemId(position: Int) =
        when (getItemView(position)) {
            ItemView.CHARACTER -> getItem(position)?.id?.toLong() ?: -1L
            ItemView.LOADING -> 0L
            ItemView.ERROR -> 1L
        }

    /**
     * The number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() =
        if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }

    /**
     * The view type of the item at position for the purposes of view recycling.
     *
     * @param position Position to query.
     * @return Integer value identifying the type of the view needed to represent at position.
     */
    override fun getItemViewType(position: Int) = getItemView(position).type

    /**
     * Update current adapter state with the new one, applying visual changes.
     *
     * @param newState State of list adapter to update.
     */
    fun submitState(newState: CharactersListAdapterState) {
        val oldState = state
        state = newState
        if (newState.hasExtraRow && oldState != newState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    /**
     * Obtain helper class to provide the number of spans each item occupies.
     *
     * @return The helper class.
     */
    fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup =
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return getItemView(position).span
            }
        }

    /**
     * Obtain the type of view by the item position.
     *
     * @param position Current item position.
     * @return ItemView type.
     */
    internal fun getItemView(position: Int) =
        if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isAddError()) {
                ItemView.ERROR
            } else {
                ItemView.LOADING
            }
        } else {
            ItemView.CHARACTER
        }
}
