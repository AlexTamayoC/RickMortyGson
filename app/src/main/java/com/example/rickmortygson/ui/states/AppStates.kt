package com.example.rickmortygson.ui.states

import com.example.rickmortygson.model.Character

sealed class AppStates {
    data object None : AppStates()
    data object Loading : AppStates()
    data class Success(val users: List<Character>) : AppStates()
    data class Error(val message: Int) : AppStates()
}