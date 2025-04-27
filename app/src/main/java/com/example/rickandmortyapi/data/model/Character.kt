package com.example.rickandmortyapi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Character (
    val name: String,
    val species: String,
    val gender: String,
    val image: String
)