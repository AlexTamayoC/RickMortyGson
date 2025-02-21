package com.example.rickmortygson.repository

import com.example.rickmortygson.model.Character
import com.example.rickmortygson.network.CharacterAPI
import com.example.rickmortygson.network.RetrofitModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepo(
    private val api: CharacterAPI = RetrofitModule.createAPI(),
    private val networkDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchCharacters(): List<Character> {
        return withContext(networkDispatcher) {
            val response = api.getCharactersPage(pageIndex = 1)
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                throw Exception("Error fetching characters: ${response.code()}")
            }
        }
    }
}


