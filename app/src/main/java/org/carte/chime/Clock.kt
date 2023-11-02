package org.carte.chime

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.carte.chime.model.TimeControl


@Composable
fun Player1Pad(modifier: Modifier = Modifier, time: Double, increment: Int) {

    Box(
        modifier = modifier,
    ) {
        Text(
            fontSize = 100.sp,
            text = "%.2f".format(time),
            modifier = Modifier.align(Alignment.Center)
        );
    }
}

@Composable
fun Player2Pad(modifier: Modifier = Modifier, time: Double, increment: Int) {

    Box(
        modifier = modifier,
    ) {
        Text(
            fontSize = 100.sp,
            text = "%.2f".format(time),
            modifier = Modifier.align(Alignment.Center)
        );
    }
}

@Composable
fun MiddleClockButtons(
    modifier: Modifier = Modifier, onResetPressed: () -> Unit,
    onEditPressed: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(onClick = onResetPressed) {
            Icon(Icons.Rounded.Refresh, "reset")
        }
        Button(onClick = onEditPressed) {
            Icon(Icons.Rounded.Edit, "edit")
        }


    }
}


@Composable
fun Clock(
    modifier: Modifier = Modifier, onResetPressed: () -> Unit, onEditPressed: () -> Unit,
    timeControl: TimeControl
) {

    var player1Time by remember { mutableStateOf(timeControl.time.toDouble()) }
    var player2Time by remember { mutableStateOf(timeControl.time.toDouble()) }
    var startGame by remember { mutableStateOf(false) }

    var playerTurn by remember { mutableStateOf(false) }

    if (startGame) {

        LaunchedEffect(playerTurn) {
            while (true) {
                delay(1000);
                if (playerTurn) {
                    player1Time -= 0.01;
                } else {
                    player2Time -= 0.01;
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Player1Pad(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray, shape = ShapeDefaults.Large)
                .weight(1f)
                .clickable {
                    if (!startGame) {
                        startGame = true;
                        playerTurn = true;
                    } else {
                        playerTurn = false;
                    }
                },

            time = player1Time,
            increment = timeControl.increment

        );
        MiddleClockButtons(onResetPressed = onResetPressed, onEditPressed = onEditPressed)

        Player2Pad(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray, shape = ShapeDefaults.Large)
                .weight(1f)
                .clickable {
                    if (!startGame) {
                        startGame = true;
                        playerTurn = false;
                    } else {
                        playerTurn = true;
                    }
                },

            time = player2Time,
            increment = timeControl.increment
        );

    }
}
