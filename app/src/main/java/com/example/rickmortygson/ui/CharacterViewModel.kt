package com.example.rickmortygson.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortygson.repository.CharacterRepo
import com.example.rickmortygson.ui.states.AppStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val characterRepo: CharacterRepo = CharacterRepo(
    )

    private val _state = MutableLiveData<AppStates>(AppStates.None)
    val state: LiveData<AppStates> = _state

    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(AppStates.Loading)
            delay(100)
            try {
                val chars = characterRepo.fetchCharacters()
                _state.postValue(AppStates.Success(listOf()))
                _state.postValue(AppStates.Success(chars))
            } catch (e: Throwable) {
                Log.e("Fetch", e.toString())
                _state.postValue(AppStates.Error(1))
            }
        }
    }
}