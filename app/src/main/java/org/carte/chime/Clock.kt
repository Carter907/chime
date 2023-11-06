package org.carte.chime

import android.widget.Toast
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
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.carte.chime.model.TimeControl
import java.util.concurrent.TimeUnit


@Composable
fun Player1Pad(
    modifier: Modifier = Modifier,
    minutes: Long,
    seconds: Long,
    increment: Int
) {

    Box(
        modifier = modifier,
    ) {
        Text(
            fontSize = 100.sp,
            text = "%d:%03d".format(minutes, seconds),
            modifier = Modifier.align(Alignment.Center)
        );
    }
}

@Composable
fun Player2Pad(
    modifier: Modifier = Modifier,
    minutes: Long,
    seconds: Long,
    increment: Int
) {

    Box(
        modifier = modifier,
    ) {
        Text(
            fontSize = 100.sp,
            text = "%d:%03d".format(minutes, seconds),
            modifier = Modifier.align(Alignment.Center)
        );
    }
}

@Composable
fun MiddleClockButtons(
    modifier: Modifier = Modifier,
    onResetPressed: () -> Unit,
    onEditPressed: () -> Unit,
    onPausePressed: () -> Unit,
    isPaused: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(onClick = onResetPressed) {
            Icon(Icons.Rounded.Refresh, "reset")
        }
        Button(onClick = onPausePressed) {
            if (!isPaused) {
                Icon(
                    painter = painterResource(R.drawable.pause_24px),
                    contentDescription = "pause"
                )
            } else {
                Icon(
                    Icons.Rounded.PlayArrow,
                    contentDescription = "play"
                )
            }

        }

        Button(onClick = onEditPressed) {
            Icon(Icons.Rounded.Edit, "edit")
        }


    }
}


@Composable
fun Clock(
    modifier: Modifier = Modifier,
    onEditPressed: () -> Unit,
    timeControl: TimeControl
) {

    var player1Time by remember {
        mutableStateOf(
            TimeUnit.MINUTES.toMillis(
                (timeControl.time).toLong()
            )
        )
    }
    var player2Time by remember {
        mutableStateOf(
            TimeUnit.MINUTES.toMillis(
                (timeControl.time).toLong()
            )
        )
    }
    // timer will go if isPlaying is true and isPaused is false
    var isPlaying by remember { mutableStateOf(false) }

    // boolean representing the player playing, if true, player1's timer is going, if false, player2's timer is going
    var playerTurn by remember { mutableStateOf(false) }

    // isPaused is used for the pause button, need for the LaunchedEffect to operate.
    var isPaused by remember { mutableStateOf(false) }

    var showWinner by remember { mutableStateOf(false) }

    var winner by remember { mutableStateOf("black") }



//    WinnerDialog(showDialog = showWinner, winner = winner) {
//
//        showWinner = false;
//    }


    if (isPlaying && !isPaused) {

        LaunchedEffect(playerTurn) {

            while (true) {

                if (player1Time <= 0 || player2Time <= 0) {
                    showWinner = true;
                    isPlaying = false;
                    isPaused = false;
                }
                delay(100);
                if (playerTurn) {
                    player1Time -= 100;
                } else {
                    player2Time -= 100;
                }
            }
        }
    } else if (!isPlaying) {


        player1Time =
            TimeUnit.MINUTES.toMillis(
                (timeControl.time).toLong()
            )
        player2Time =
            TimeUnit.MINUTES.toMillis(
                (timeControl.time).toLong()
            )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val context = LocalContext.current

        Player1Pad(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray, shape = ShapeDefaults.Large)
                .weight(1f)
                .clickable {
                    if (!isPlaying) {
                        isPlaying = true;
                        playerTurn = false;
                    } else if (!isPaused) {
                        playerTurn = false;
                    }
                },

            minutes = player1Time / 60000,
            seconds = (player1Time / 100) % 600,
            increment = timeControl.increment

        );

        MiddleClockButtons(
            onResetPressed = {

                isPlaying = false;
                isPaused = false;

            },
            onEditPressed = onEditPressed,
            onPausePressed = {

                if (!isPlaying) {
                    Toast.makeText(context, "press pad to start opponent's timer", Toast.LENGTH_SHORT).show()
                } else {

                    isPaused = !isPaused;
                }





            },
            isPaused = isPaused

        )

        Player2Pad(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray, shape = ShapeDefaults.Large)
                .weight(1f)
                .clickable {
                    if (!isPlaying) {
                        isPlaying = true;
                        playerTurn = true;
                    } else if (!isPaused) {
                        playerTurn = true;
                    }
                },

            minutes = player2Time / 60000,
            seconds = (player2Time / 100) % 600,
            increment = timeControl.increment
        );

    }


}

@Composable
fun WinnerDialog(showDialog: Boolean, onDismissRequest: () -> Unit, winner: String) {

    if (showDialog) {
        AlertDialog(onDismissRequest = onDismissRequest, confirmButton = {
            Button(onClick = {
                onDismissRequest()
            }) {
                Text("reset")
            }
        },
            text = {
                Text("$winner has won on time!")
            }

        )
    }

}