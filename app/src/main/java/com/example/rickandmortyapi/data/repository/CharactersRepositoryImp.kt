package com.example.rickandmortyapi.data.repository

import com.example.rickandmortyapi.data.dao.CharacterDao
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.model.CharacterEntity
import com.example.rickandmortyapi.data.network.RickMortyApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersRepositoryImp (
    private val rickMortyApiService: RickMortyApiService,
    private val characterDao: CharacterDao
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        val remoteCharacters = rickMortyApiService.getCharacters().results
        val entities = remoteCharacters.map {
            CharacterEntity(
                id = it.id.toInt(),
                name = it.name,
                species = it.species,
                gender = it.gender,
                image = it.image
            )
        }
        characterDao.insertCharacters(entities)
        return remoteCharacters
    }

    override fun observeCharacters(): Flow<List<Character>> {
        return characterDao.getAllCharacters().map { entities ->
            entities.map { entity ->
                Character(
                    id = entity.id,
                    name = entity.name,
                    species = entity.species,
                    gender = entity.gender,
                    image = entity.image,
                )
            }
        }
    }
}