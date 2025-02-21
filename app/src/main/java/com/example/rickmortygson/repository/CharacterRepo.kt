package com.example.rickmortygson.repository

import com.example.rickmortygson.network.CharacterAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.rickmortygson.model.Character
import com.example.rickmortygson.model.CharactersPageResponse
import com.example.rickmortygson.network.RetrofitModule

class CharacterRepo (
    private val characterAPI: CharacterAPI = RetrofitModule.createAPI(), private val networkDispatcher: CoroutineDispatcher = Dispatchers.IO
){
    suspend fun fetchCharacters(): CharactersPageResponse? {
        return withContext(Dispatchers.IO) {
            return@withContext characterAPI.getCharactersPage(1).body()
        }
    }
}