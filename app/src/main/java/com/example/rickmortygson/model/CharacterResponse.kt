package com.example.rickmortygson.model

import com.example.rickmortygson.model.Character

data class CharacterResponse(
    val characters: List<Character>,
    val pageInfo: PageInfo
)
