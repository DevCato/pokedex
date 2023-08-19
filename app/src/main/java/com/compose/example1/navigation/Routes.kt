package com.compose.example1.navigation

sealed class Routes (val route: String){
    object DashboardRoute: Routes("dashboard")
    object RestaurantRoute : Routes("restaurant")
}