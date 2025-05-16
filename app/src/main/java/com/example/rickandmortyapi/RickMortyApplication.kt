package com.example.rickandmortyapi

import android.app.Application
import androidx.work.Configuration
import com.example.rickandmortyapi.data.AppContainer
import com.example.rickandmortyapi.data.DefaultAppContainer
import com.example.rickandmortyapi.workers.RickAndMortyWorkerFactory

class RickMortyApplication : Application(), Configuration.Provider {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(RickAndMortyWorkerFactory(container.charactersRepository))
            .build()

}