package com.example.rickandmortyapi.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.rickandmortyapi.data.DefaultAppContainer
import com.example.rickandmortyapi.data.repository.CharactersRepository

class RickAndMortySyncWorker(
    context: Context,
    params: WorkerParameters,
    private val repository: CharactersRepository
) : CoroutineWorker(context, params) {

    constructor(context: Context, params: WorkerParameters) : this(
        context,
        params,
        DefaultAppContainer(context).charactersRepository
    )

    override suspend fun doWork(): Result {
        return try {
            Log.d("Worker", "✅ Synchronizing characters from API...")
            val characters = repository.getCharacters()
            Log.d("Worker", "✅ Worker synchronized: ${characters.size} characters")
            Result.success()
        } catch (e: Exception) {
            Log.d("Worker", "❌ Error in synchronizing the characters: ${e.message}")
            Result.retry()
        }
    }
}