package com.example.rickandmortyapi

import android.app.Application
import com.example.rickandmortyapi.data.AppContainer
import com.example.rickandmortyapi.data.DefaultAppContainer

class RickMortyApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}