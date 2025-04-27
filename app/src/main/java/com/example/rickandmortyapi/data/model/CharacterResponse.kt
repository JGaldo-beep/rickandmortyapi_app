package com.example.rickandmortyapi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val results: List<Character>
)
