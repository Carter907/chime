package org.carte.chime

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ChessTimer(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "title") {
        composable("title") {
            Title(
                onContinuePressed = {
                    navController.navigate("time-control")
                }
            )
        }

        composable("clock") {
            Clock(
                onResetPressed = {},
                onEditPressed = {navController.navigate("time-control")}
            )
        }
        composable("time-control") {
            TimeControl {}
        }
    }
}