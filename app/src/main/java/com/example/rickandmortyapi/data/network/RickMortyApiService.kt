package com.example.rickandmortyapi.data.network

import com.example.rickandmortyapi.data.model.CharacterResponse
import retrofit2.http.GET

interface RickMortyApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}