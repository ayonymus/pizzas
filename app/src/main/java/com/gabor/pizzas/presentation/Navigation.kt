package com.gabor.pizzas.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gabor.pizzas.presentation.order.OrderRoute
import com.gabor.pizzas.presentation.summary.SummaryRoute

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Order.route
    ) {
        composable(
            Routes.Order.route
        ) {
            OrderRoute(
                onNavigateToOrder = { p1, p2 ->
                    navController.navigate(Routes.Summary.createRoute(p1.name, p1.price.toFloat(), p2?.name, p2?.price?.toFloat()))
                }
            )
        }

        composable(
            Routes.Summary.route,
            arguments = listOf(
                navArgument("mainName") { type = NavType.StringType},
                navArgument("mainPrice") { type = NavType.FloatType},
                navArgument("otherName") {
                    nullable = true
                    type = NavType.StringType
                },
                navArgument("otherPrice") {
                    defaultValue = 0f
                    type = NavType.FloatType
                },
                )
        ) {
            SummaryRoute()
        }
    }
}

sealed class Routes(val route: String) {
    object Order : Routes("order")
    // Note, it is discouraged to pass structured data during navigation, as it makes deep linking hard.
    // However, since we don't have stable IDs for the particular Pizzas, for this implementation I just pass the data needed
    // for displaying the summary to save some time.
    // In a real project I would have a single source of truth, e.g. a repository storing Pizzas with stable ids.
    object Summary : Routes("summary/{mainName}/{mainPrice}?otherName={otherName}otherPrice={otherPrice}") {
        fun createRoute(p1Name: String, p1Price: Float, p2Name: String?, p2Price: Float?) =
            "summary/$p1Name/$p1Price?otherName=$p2Name?otherPrice=$p2Price"
    }
}
