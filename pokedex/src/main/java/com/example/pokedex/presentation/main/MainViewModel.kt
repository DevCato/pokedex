package com.example.pokedex.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.usecases.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val pokemonList = getPokemonsUseCase.execute()
            Log.e(TAG, "onClickPokemon: $pokemonList")
            _state.value = _state.value.copy(pokemonList = pokemonList, isLoading = false)
        }
    }

    fun onClickPokemon(pokemon: Pokemon) {
        _state.value = _state.value.copy(selectedPokemon = pokemon)
    }
}