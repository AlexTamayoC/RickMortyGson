package com.example.rickmortygson.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortygson.R
import com.example.rickmortygson.model.Character
import com.example.rickmortygson.repository.CharacterRepo
import com.example.rickmortygson.ui.states.AppStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepo: CharacterRepo
) : ViewModel() {

    private val _state = MutableLiveData<AppStates>(AppStates.None)
    val state: LiveData<AppStates> = _state

    private var allCharacters = mutableListOf<Character>()
    private var currentPage = 1
    private var totalPages = Int.MAX_VALUE
    private var currentQuery: String? = null

    fun fetchCharacters(query: String? = currentQuery) {
        if (currentPage > totalPages) return

        _state.value = AppStates.Loading
        viewModelScope.launch {
            try {
                val response = characterRepo.fetchCharacters(currentPage, query)
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

    fun resetSearch(query: String?) {
        currentQuery = query
        currentPage = 1
        totalPages = Int.MAX_VALUE
        allCharacters.clear()
        fetchCharacters(query)
    }
}




