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
package io.audioshinigami.characters.detail

import io.audioshinigami.ui.BaseViewState

/**
 * Different states for [CharacterDetailFragment].
 *
 * @see BaseViewState
 */
sealed class CharacterDetailViewState : BaseViewState {

    /**
     * Loading character detail info.
     */
    object Loading : CharacterDetailViewState()

    /**
     * Error on loading character detail info.
     */
    object Error : CharacterDetailViewState()

    /**
     * Add current character to favorite list.
     */
    object AddToFavorite : CharacterDetailViewState()

    /**
     * Added current character to favorite list.
     */
    object AddedToFavorite : CharacterDetailViewState()

    /**
     * Already added character to favorite list.
     */
    object AlreadyAddedToFavorite : CharacterDetailViewState()

    /**
     * Dismiss the detail view.
     */
    object Dismiss : CharacterDetailViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [AddToFavorite].
     *
     * @return True if is add to favorite state, otherwise false.
     */
    fun isAddToFavorite() = this is AddToFavorite

    /**
     * Check if current view state is [AddedToFavorite].
     *
     * @return True if is added to favorite state, otherwise false.
     */
    fun isAddedToFavorite() = this is AddedToFavorite

    /**
     * Check if current view state is [AlreadyAddedToFavorite].
     *
     * @return True if is already added to favorite state, otherwise false.
     */
    fun isAlreadyAddedToFavorite() = this is AlreadyAddedToFavorite

    /**
     * Check if current view state is [Dismiss].
     *
     * @return True if is delete state, otherwise false.
     */
    fun isDismiss() = this is Dismiss
}
