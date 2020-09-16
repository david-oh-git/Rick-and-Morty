package io.audioshinigami.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class HomeViewModel: ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state : LiveData<HomeViewState>
        get() = _state

    fun navigationControllerChanged(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // TODO : logic to set HomeViewState
        }
    }

}