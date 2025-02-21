package com.example.rickmortygson.network

import com.example.rickandmortyclean.model.CharacterResponse
import retrofit2.http.GET

interface CharacterAPI{
    @GET("character")
    suspend fun fetchCharacters() : CharacterResponse