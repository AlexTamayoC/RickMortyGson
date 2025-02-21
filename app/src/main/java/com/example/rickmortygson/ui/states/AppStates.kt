package com.example.rickmortygson.ui.states

sealed class AppStates<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : AppStates<T>(data)

    class Loading<T>(data: T? = null) : AppStates<T>(data)

    class Error<T>(message: String, data: T? = null) : AppStates<T>(data, message)

}