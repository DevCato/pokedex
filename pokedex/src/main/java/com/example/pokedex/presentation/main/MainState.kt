package com.example.pokedex.presentation.main

import com.example.pokedex.domain.Pokemon

data class MainState(
    val pokemonList: List<Pokemon> = emptyList(),
    val searchText: String = String(),
    val isLoading: Boolean = false,
    val selectedPokemon: Pokemon? = null
) {

    val searchPokemonList: List<Pokemon>
        get() = pokemonList.filter {
            it.name.contains(searchText, ignoreCase = true)
        }
}
