package com.example.furnishioz

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.furnishioz.ui.navigation.NavigationItem
import com.example.furnishioz.ui.navigation.Screen
import com.example.furnishioz.ui.screen.cart.CartScreen
import com.example.furnishioz.ui.screen.detail.DetailProductScreen
import com.example.furnishioz.ui.screen.home.HomeScreen
import com.example.furnishioz.ui.screen.profile.ProfileScreen
import com.example.furnishioz.ui.screen.search.SearchScreen
import com.example.furnishioz.ui.theme.FurnishiozTheme

@Composable
fun FurnishiozApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailProduct.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId))
                    },
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId))
                    },
                )
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(
                    onOrderButtonClicked = { message ->
                        showMessage(context, message)
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailProduct.route,
                arguments = listOf(navArgument("productId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("productId") ?: -1L
                DetailProductScreen(
                    productId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentState = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                icon = Icons.Outlined.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.search),
                icon = Icons.Outlined.Search,
                screen = Screen.Search
            ),
            NavigationItem(
                title = stringResource(R.string.cart),
                icon = Icons.Outlined.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.profile),
                icon = Icons.Outlined.Person,
                screen = Screen.Profile
            ),
        )
        navigationItem.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentState == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}


private fun showMessage(context: Context, message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.total))
        putExtra(Intent.EXTRA_TEXT, message)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.total)
        )
    )
}


@Preview(showBackground = true)
@Composable
fun FurnishiozAppPreview() {
    FurnishiozTheme {
        FurnishiozApp()
    }
}