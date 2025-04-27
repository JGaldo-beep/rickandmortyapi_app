package com.example.rickandmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.rickandmortyapi.ui.RickAndMortyApp
import com.example.rickandmortyapi.ui.theme.RickAndMortyAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAPITheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RickAndMortyApp()
                }
            }
        }
    }
}