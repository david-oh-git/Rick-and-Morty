package io.audioshinigami.characters_list.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.audioshinigami.characters_list.detail.models.CharacterDetail

class CharacterDetailViewModel @ViewModelInject constructor(): ViewModel() {

    private val _data = MutableLiveData<CharacterDetail>()
    val data: LiveData<CharacterDetail>
        get() = _data

    private val _state = MutableLiveData<CharacterDetailViewState>()
    val state: LiveData<CharacterDetailViewState>
        get() = _state

    fun addCharacterToFavorite(){
    }

    fun dismissCharacterDetail(){
        _state.postValue( CharacterDetailViewState.Dismiss )
    }

    fun setData(characterDetail: CharacterDetail){
        _data.postValue(characterDetail)
    }
}