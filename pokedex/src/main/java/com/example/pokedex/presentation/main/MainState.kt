package com.example.pokedex.presentation.main

import com.example.pokedex.domain.Pokemon

data class MainState (
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val selectedPokemon : Pokemon? = null
)
