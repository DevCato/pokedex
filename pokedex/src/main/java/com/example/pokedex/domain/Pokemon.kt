package com.example.pokedex.domain

data class Pokemon(
    val code : Int,
    val name: String
){
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$code.png"
}