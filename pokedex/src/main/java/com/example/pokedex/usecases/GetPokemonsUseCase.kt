package com.example.pokedex.usecases

import com.example.pokedex.domain.Pokemon

class GetPokemonsUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun execute(): List<Pokemon> =
        pokemonRepository.getPokemons()

}