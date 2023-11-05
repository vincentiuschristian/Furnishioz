package com.example.furnishioz.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailProduct : Screen("home/{productId}"){
        fun createRoute(productId: Long) = "home/$productId"
    }
}