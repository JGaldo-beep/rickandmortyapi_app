package com.example.rickandmortyapi.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.ui.screens.CharacterListScreen
import com.example.rickandmortyapi.ui.screens.RickMortyViewModel

@Composable
fun RickAndMortyApp() {
    Scaffold(
        topBar = {
            RickAndMortyTopAppBar()
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            val rickMortyViewModel: RickMortyViewModel = viewModel(factory = RickMortyViewModel.Factory)
            CharacterListScreen(viewModel = rickMortyViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.title_top_bar),
                style = typography.headlineSmall
            )
        }
    )
}