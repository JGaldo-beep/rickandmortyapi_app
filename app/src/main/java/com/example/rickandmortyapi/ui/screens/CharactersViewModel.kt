package com.example.rickandmortyapi.ui.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.rickandmortyapi.RickMortyApplication
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.repository.CharactersRepository
import com.example.rickandmortyapi.workers.RickAndMortySyncWorker
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

sealed interface CharactersUiState {
    object Loading : CharactersUiState
    data class Success(val values: List<Character>) : CharactersUiState
    object Error : CharactersUiState
}

class RickMortyViewModel(
    private val charactersRepository: CharactersRepository,
    private val appContext: Context
) : ViewModel() {

    val uiState: StateFlow<CharactersUiState> = charactersRepository.observeCharacters()
        .map { characters ->
            CharactersUiState.Success(characters) as CharactersUiState
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharactersUiState.Loading
        )

    init {
        scheduleCharacterSync()
    }

    private fun scheduleCharacterSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val periodicRequest = PeriodicWorkRequestBuilder<RickAndMortySyncWorker>(
            15, TimeUnit.MINUTES
        ).setConstraints(constraints)
            .build()

        val oneTimeRequest = OneTimeWorkRequestBuilder<RickAndMortySyncWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(appContext)

        // Periodic (15 min)
        workManager.enqueueUniquePeriodicWork(
            "RickAndMortyPeriodicSync",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )

        // One-time
        workManager.enqueue(oneTimeRequest)
    }


    fun getResources() {
        viewModelScope.launch {
            try {
                charactersRepository.getCharacters()
            } catch (e: IOException) {
                CharactersUiState.Error

            } catch (e: HttpException) {
                CharactersUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RickMortyApplication)
                val rickMortyRepository = application.container.charactersRepository
                RickMortyViewModel(
                    charactersRepository = rickMortyRepository,
                    appContext = application.applicationContext
                )
            }
        }
    }
}