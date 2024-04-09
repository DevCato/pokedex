package com.example.pokedex.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.circularShapeBackground
import kotlinx.coroutines.delay

@Composable
fun CircularShape(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(circularShapeBackground),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun RoundedCornerShape(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val showShimmer = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(circularShapeBackground)
            .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value))
    ) {
        content()
    }

    LaunchedEffect(Unit){
        delay(1000)
        showShimmer.value = false
    }
}
