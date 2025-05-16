package com.example.rickandmortyapi.workers

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.rickandmortyapi.data.repository.CharactersRepository

class RickAndMortyWorkerFactory(
    private val repository: CharactersRepository
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        Log.d("WorkerFactory", "✅ Se está usando WorkerFactory para: $workerClassName")
        return when (workerClassName) {
            RickAndMortySyncWorker::class.java.name ->
                RickAndMortySyncWorker(appContext, workerParameters, repository)
            else -> null
        }
    }
}