package com.example.rickandmortyapi.data.repository

import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.network.RickMortyApiService

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
}

class NetworkCharactersRepository(
    private val rickMortyApiService: RickMortyApiService
) : CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        return rickMortyApiService.getCharacters().results
    }
}