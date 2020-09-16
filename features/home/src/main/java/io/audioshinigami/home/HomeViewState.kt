package io.audioshinigami.home

import io.audioshinigami.ui.BaseViewState

/**
 * Different states of [HomeFragment]
 */

sealed class HomeViewState: BaseViewState {

    /**
     * Fullscreen mode.
     */
    object FullScreen: HomeViewState()

    /**
     * Navigation screen mode with visible bottom bar.
     */
    object NavigationScreen: HomeViewState()

    /**
     *  Checks if current view is [FullScreen].
     *  @return True if fullscreen.
     */
    fun isFullScreen() = this is FullScreen

    /**
     * Checks if current view is [NavigationScreen].
     * @return True if navigation screen.
     */
    fun isNavigationScreen() = this is NavigationScreen
}