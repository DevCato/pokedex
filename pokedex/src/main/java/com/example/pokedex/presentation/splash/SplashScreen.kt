package com.example.pokedex.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pokedex.R
import com.example.pokedex.presentation.navigation.Routes
import com.example.pokedex.ui.theme.SurfaceDark
import com.example.pokedex.ui.theme.circularShapeBackground

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceDark)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_splash))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition, speed = 1.3f, clipSpec = LottieClipSpec.Frame(0, 110))
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress },
            modifier = Modifier.align(Alignment.Center)
        )
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            navController.navigate(Routes.PrincipalRoute.route)
        }
    }
}