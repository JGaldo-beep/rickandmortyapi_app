package com.example.rickandmortyapi.data.repository

import com.example.rickandmortyapi.data.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>

    fun observeCharacters(): Flow<List<Character>>
}