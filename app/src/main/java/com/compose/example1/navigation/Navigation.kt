package com.compose.example1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.example1.screens.dashboard.DashboardFragment
import com.compose.example1.screens.restaurant.RestaurantFragment

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.DashboardRoute.route) {
        composable(Routes.DashboardRoute.route) {
            DashboardFragment {
                navController.navigate(Routes.RestaurantRoute.route)
            }
        }
        composable(Routes.RestaurantRoute.route) { RestaurantFragment(/*...*/) }
    }
}