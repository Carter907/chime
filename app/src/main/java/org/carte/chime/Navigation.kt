package org.carte.chime

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ChessTimer(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "title") {
        composable("title") {
            Title(navController = navController)
        }

        composable("clock") {
            Clock()
        }
    }
}