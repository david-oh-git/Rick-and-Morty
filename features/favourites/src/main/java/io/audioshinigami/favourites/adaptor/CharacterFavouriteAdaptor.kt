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
package io.audioshinigami.favourites.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.favourites.adaptor.holders.CharacterFavouriteViewHolder
import io.audioshinigami.ui.base.BaseListAdaptor

/**
 * Adaptor for the [RecyclerView] that represents the list of [CharacterFavourite] stored
 * in the database.
 */
internal class CharacterFavouriteAdaptor : BaseListAdaptor<CharacterFavourite>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    /**
     * Called when recyclerView needs a new [RecyclerView.ViewHolder] for the view type.
     * @param parent The parent view into which the new ViewHolder is added.
     * @param inflater Instantiate the xml layout for the view.
     * @param viewType Specifies the new view type.
     * @return A new viewHolder for the viewType.
     *
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = CharacterFavouriteViewHolder(inflater)

    /**
     * Called by recyclerView to display data for a specified position.
     *
     * @param holder The viewHolder who's data is to be updated.
     * @param position The viewHolder's position.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterFavouriteViewHolder -> holder.bind(getItem(position))
        }
    }
}
