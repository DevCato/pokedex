package com.example.pokedex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ImageWithUrl(imageUrl: String, modifier: Modifier = Modifier) {

    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            transformations(CircleCropTransformation())
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .padding(0.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}
