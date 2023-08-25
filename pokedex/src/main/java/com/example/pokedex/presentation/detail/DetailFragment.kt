package com.example.pokedex.presentation.detail

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.ui.components.common.CircularShape
import com.example.pokedex.ui.components.common.ImageWithUrl
import com.example.pokedex.ui.theme.water
import com.example.pokedex.ui.theme.watergrass

@Composable
fun DetailFragment(pokemon: Pokemon?) {
    val gradientColors = listOf(water, watergrass)
    val brush = Brush.verticalGradient(gradientColors)
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotationAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ), label = ""
    )
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            CircularShape(modifier = Modifier
                .size(280.dp)
                .drawBehind {
                    rotate(rotationAnimation.value) {
                        drawCircle(
                            brush = brush, style = Stroke(10f)
                        )
                    }
                }) {
                ImageWithUrl(imageUrl = pokemon?.imageUrl ?: "", modifier = Modifier.padding(30.dp))
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = pokemon?.name ?: "No pokemon selected",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                TypeChip("Water")
                TypeChip("Fire")
                TypeChip("Grass")
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun TypeChip(text: String = "Water") {
    val gradientColors = listOf(water, watergrass)
    val brush = Brush.horizontalGradient(gradientColors)

    Box(
        modifier = Modifier
            .widthIn()
            .border(1.dp, brush, RoundedCornerShape(7.dp))
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 3.dp),
            style = TextStyle(
                brush = brush
            )
        )
    }
}

@Preview
@Composable
fun DetailFragment() {
    DetailFragment(
        pokemon = Pokemon(
            1,
            "Bulbasaur"
        )
    )
}