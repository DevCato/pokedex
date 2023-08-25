package com.example.pokedex.usecases

import com.example.pokedex.domain.Pokemon

interface PokemonRepository {
    suspend fun getPokemons() : List<Pokemon>

}
