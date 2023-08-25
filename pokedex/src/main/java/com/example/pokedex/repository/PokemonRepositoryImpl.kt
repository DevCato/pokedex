package com.example.pokedex.repository

import com.example.pokedex.domain.Pokemon
import com.example.pokedex.repository.mappers.toDomain
import com.example.pokedex.usecases.PokemonRepository

class PokemonRepositoryImpl(private val pokemonDataSource: PokemonDataSource) : PokemonRepository {
    override suspend fun getPokemons(): List<Pokemon> {
        val pokemonsEntityList = pokemonDataSource.getPokemons().results
        return pokemonsEntityList.map { it.toDomain() }
    }
}