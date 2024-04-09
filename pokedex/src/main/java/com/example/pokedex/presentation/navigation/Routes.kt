package com.example.pokedex.presentation.navigation

sealed class Routes (val route: String){
    object PrincipalRoute: Routes("principal")
    object DetailRoute : Routes("detail")
    object SplashRoute : Routes("splash")
}