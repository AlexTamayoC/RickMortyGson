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
import com.example.rickmortygson.model.Character

class CharacterViewModel : ViewModel() {

    private val characterRepo = CharacterRepo()

    private val _state = MutableLiveData<AppStates>(AppStates.None)
    val state: LiveData<AppStates> = _state

    private val allCharacters = mutableListOf<Character>()
    private var currentPage = 1
    private var totalPages = Int.MAX_VALUE

    fun fetchCharacters() {
        if (currentPage > totalPages) return

        _state.value = AppStates.Loading
        viewModelScope.launch {
            try {
                val response = characterRepo.fetchCharacters(currentPage)
                totalPages = response.info.pages
                allCharacters.addAll(response.results)
                _state.value = AppStates.Success(allCharacters.toList())
                currentPage++
            } catch (e: Throwable) {
                Log.e("Fetch", e.toString())
                _state.value = AppStates.Error(R.string.error_message)
            }
        }
    }
}



