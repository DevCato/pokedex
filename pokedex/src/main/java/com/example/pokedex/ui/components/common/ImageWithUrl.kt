package com.example.pokedex.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.pokedex.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

@Composable
fun CoilImageWithUrl(imageUrl: String, modifier: Modifier = Modifier) {

    AsyncImage(
        model = imageUrl,
        contentDescription = String(),
        modifier = modifier.padding(0.dp),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.placeholder)
    )
}