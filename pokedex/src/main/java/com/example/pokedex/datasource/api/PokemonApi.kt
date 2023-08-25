package com.example.pokedex.datasource.api

import com.example.pokedex.domain.network.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon?limit=80")
    suspend fun getPokemons(): Response<PokemonListResponse>

}