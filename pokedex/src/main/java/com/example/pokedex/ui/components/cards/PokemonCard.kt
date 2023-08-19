package com.example.pokedex.ui.components.cards

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.pokedex.ui.components.ImageWithUrl
import com.example.pokedex.ui.components.RoundedCornerShape

@Composable
fun PokemonItem(modifier: Modifier = Modifier, pokemonId: Int = 0) {

    val urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"

    RoundedCornerShape(modifier = modifier.heightIn(min = 32.dp)) {
        ConstraintLayout(modifier = Modifier.width(170.dp)) {
            val (code, name, image) = createRefs()

            Text("#001", modifier = Modifier.constrainAs(code) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
            )
            Text("Bulbasur", modifier = Modifier.constrainAs(name) {
                top.linkTo(code.bottom, margin = 0.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
            )
            ImageWithUrl(
                imageUrl = urlImage,
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(name.bottom, margin = (-10).dp)
                        end.linkTo(parent.end, margin = (-10).dp)
                    }
                    .size(150.dp)
                    .padding(start = 20.dp)
            )
        }
    }
}