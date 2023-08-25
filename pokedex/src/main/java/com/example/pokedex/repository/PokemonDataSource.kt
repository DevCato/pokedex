package com.example.pokedex.repository

import com.example.pokedex.domain.network.PokemonListResponse

interface PokemonDataSource {
    suspend fun getPokemons() : PokemonListResponse

}
