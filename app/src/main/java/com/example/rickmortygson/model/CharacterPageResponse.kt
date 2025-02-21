package com.example.rickmortygson.model

class CharactersPageResponse(
    val info: Info = Info(),
    val results: List<Character> = emptyList()
) {
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}