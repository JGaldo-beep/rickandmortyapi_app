package com.example.rickandmortyapi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Character (
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val image: String
)