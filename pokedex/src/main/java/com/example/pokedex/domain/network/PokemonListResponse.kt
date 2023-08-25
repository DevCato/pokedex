package com.example.pokedex.domain.network

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonEntity>
)