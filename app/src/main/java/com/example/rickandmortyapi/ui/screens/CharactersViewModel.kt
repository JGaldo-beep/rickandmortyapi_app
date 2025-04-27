package com.example.rickandmortyapi.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickandmortyapi.RickMortyApplication
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CharactersUiState {
    object Loading : CharactersUiState
    data class Success(val values: List<Character>) : CharactersUiState
    object Error : CharactersUiState
}

class RickMortyViewModel (
    private val charactersRepository: CharactersRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        getResources()
    }

    fun getResources() {
        viewModelScope.launch {
            try {
                val characters = charactersRepository.getCharacters()
                _uiState.update {
                    CharactersUiState.Success(characters)
                }
            } catch (e: IOException) {
                _uiState.update {
                    CharactersUiState.Error
                }
            } catch (e: HttpException) {
                _uiState.update {
                    CharactersUiState.Error
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RickMortyApplication)
                val rickMortyRepository = application.container.charactersRepository
                RickMortyViewModel(charactersRepository = rickMortyRepository)
            }
        }
    }
}