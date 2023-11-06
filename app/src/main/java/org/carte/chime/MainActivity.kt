package org.carte.chime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.carte.chime.model.TimeControl
import org.carte.chime.ui.theme.ChessTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessTimerTheme (darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var timeControlSelection by remember { mutableStateOf(TimeControl(0.0,0.0))}

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
                                onEditPressed = {navController.navigate("time-control")},
                                timeControl = timeControlSelection
                            )
                        }
                        composable("time-control") {
                            TimeControlSelection(
                                onSelectionPressed = {
                                    timeControlSelection = it;
                                    navController.navigate("clock")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}



