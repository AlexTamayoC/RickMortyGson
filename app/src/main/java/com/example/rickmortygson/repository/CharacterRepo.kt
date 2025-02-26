package com.example.rickmortygson.repository

import com.example.rickmortygson.model.CharactersPageResponse
import com.example.rickmortygson.network.CharacterAPI
import com.example.rickmortygson.repository.IoDispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepo @Inject constructor(
    private val api: CharacterAPI,
    @IoDispatcher private val networkDispatcher: CoroutineDispatcher
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





