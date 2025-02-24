package com.example.rickmortygson.repository

import com.example.rickmortygson.model.CharactersPageResponse
import com.example.rickmortygson.network.CharacterAPI
import com.example.rickmortygson.network.RetrofitModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepo(
    private val api: CharacterAPI = RetrofitModule.createAPI(),
    private val networkDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchCharacters(page: Int, query: String? = null): CharactersPageResponse {
        return withContext(networkDispatcher) {
            val response = api.getCharactersPage(pageIndex = page, name = query)
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Empty response")
            } else {
                throw Exception("Error fetching characters: ${response.code()}")
            }
        }
    }
}




