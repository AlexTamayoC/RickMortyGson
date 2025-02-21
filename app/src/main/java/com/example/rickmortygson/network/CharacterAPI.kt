package com.example.rickmortygson.network

import com.example.rickmortygson.model.CharactersPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {
    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<CharactersPageResponse>
}
