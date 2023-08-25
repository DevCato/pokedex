package com.example.pokedex.datasource

import com.example.pokedex.datasource.api.PokemonApi
import com.example.pokedex.domain.network.PokemonListResponse
import com.example.pokedex.repository.PokemonDataSource
import javax.inject.Inject

class NetworkPokemonDataSource @Inject constructor(private val pokemonApi: PokemonApi) :
    PokemonDataSource {
    override suspend fun getPokemons() : PokemonListResponse {
        val response = pokemonApi.getPokemons()
        if (response.isSuccessful.not()) throw Exception("Response is not successful")
        if (response.body() == null) throw Exception("Response body is null")
        return response.body() ?: throw Exception("Response body is null")
    }
}