package io.audioshinigami.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.audioshinigami.characters_list.R.id.characters_list_fragment

val NAV_FRAGMENTS_ID = setOf(
    characters_list_fragment
)

class HomeViewModel @ViewModelInject constructor() : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState>
        get() = _state

    fun navigationControllerChanged(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (NAV_FRAGMENTS_ID.contains(destination.id)) {
                _state.postValue(HomeViewState.NavigationScreen)
            } else {
                _state.postValue(HomeViewState.FullScreen)
            }
        }
    }
}
