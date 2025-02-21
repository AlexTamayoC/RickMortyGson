package com.example.rickmortygson.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortygson.R
import com.example.rickmortygson.repository.CharacterRepo
import com.example.rickmortygson.ui.states.AppStates
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val characterRepo = CharacterRepo()

    private val _state = MutableLiveData<AppStates>(AppStates.None)
    val state: LiveData<AppStates> = _state

    fun fetchCharacters() {
        viewModelScope.launch {
            _state.value = AppStates.Loading
            try {
                val characters = characterRepo.fetchCharacters()
                _state.value = AppStates.Success(characters)
            } catch (e: Throwable) {
                Log.e("Fetch", e.toString())
                _state.value = AppStates.Error(R.string.error_message)
            }
        }
    }
}


