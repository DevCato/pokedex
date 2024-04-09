package com.example.pokedex.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.presentation.detail.DetailFragment
import com.example.pokedex.presentation.main.MainState
import com.example.pokedex.presentation.main.PrincipalFragment
import com.example.pokedex.presentation.splash.SplashScreen
import com.example.pokedex.ui.theme.MyApplicationTheme

@Composable
fun Navigation(
    mainState: State<MainState>,
    onClickPokemon: (Pokemon) -> Unit,
    selectedPokemon: Pokemon? = null,
    onSearchPokemon: (String) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashRoute.route) {
        composable(Routes.SplashRoute.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(Routes.PrincipalRoute.route) {
            PrincipalFragment(
                mainState = mainState,
                onClickPokemon = onClickPokemon,
                onSearchPokemon = onSearchPokemon
            ) {
                navController.navigate(Routes.DetailRoute.route)
            }
        }
        composable(Routes.DetailRoute.route) {
            DetailFragment(pokemon = selectedPokemon, onBack = { navController.popBackStack() })
        }
    }
}