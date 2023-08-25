package com.example.pokedex.di

import com.example.pokedex.datasource.NetworkPokemonDataSource
import com.example.pokedex.datasource.api.PokemonApi
import com.example.pokedex.presentation.main.MainViewModel
import com.example.pokedex.repository.PokemonDataSource
import com.example.pokedex.repository.PokemonRepositoryImpl
import com.example.pokedex.usecases.GetPokemonsUseCase
import com.example.pokedex.usecases.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class PokemonModule {
    @Provides
    fun bindPokemonDataSource(
        pokemonApi: PokemonApi
    ): PokemonDataSource = NetworkPokemonDataSource(pokemonApi)

    @Provides
    fun bindPokemonRepository(
        pokemonDataSource: NetworkPokemonDataSource
    ): PokemonRepository = PokemonRepositoryImpl(pokemonDataSource)

    @Provides
    fun bindGetPokemonUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonsUseCase = GetPokemonsUseCase(pokemonRepository)

    @Provides
    fun bindViewModel(
        getPokemonsUseCase: GetPokemonsUseCase
    ): MainViewModel = MainViewModel(getPokemonsUseCase)

}