package com.example.rickandmortyapi.data

import android.content.Context
import com.example.rickandmortyapi.data.database.DatabaseRickAndMorty
import com.example.rickandmortyapi.data.network.RickMortyApiService
import com.example.rickandmortyapi.data.repository.CharactersRepository
import com.example.rickandmortyapi.data.repository.CharactersRepositoryImp
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val charactersRepository: CharactersRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl =
        "https://rickandmortyapi.com/api/"

    val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: RickMortyApiService by lazy {
        retrofit.create(RickMortyApiService::class.java)
    }

    override val charactersRepository: CharactersRepository by lazy {
        CharactersRepositoryImp(
            rickMortyApiService = retrofitService,
            characterDao = DatabaseRickAndMorty.getDatabase(context).characterDao()
        )
    }
}