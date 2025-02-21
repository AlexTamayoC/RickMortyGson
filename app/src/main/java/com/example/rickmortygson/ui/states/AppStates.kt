package com.example.rickmortygson.ui.states

import com.example.rickmortygson.model.Character

sealed class AppStates {
    object None : AppStates()
    object Loading : AppStates()
    data class Success(val characters: List<Character>) : AppStates()
    data class Error(val message: Int) : AppStates()
}
