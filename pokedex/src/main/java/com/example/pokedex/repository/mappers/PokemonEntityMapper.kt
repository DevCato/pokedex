package com.example.pokedex.repository.mappers

import androidx.compose.ui.text.capitalize
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.domain.network.PokemonEntity
import java.util.Locale

fun PokemonEntity.toDomain(): Pokemon {

    val pokemonId =
        url.replace(Regex("[^0-9]"), "").substring(startIndex = 1) // get the id from the url

    return Pokemon(
        code = pokemonId.toInt(),
        name = name.replaceFirstChar { it.titlecase(Locale.ROOT) },
    )
}