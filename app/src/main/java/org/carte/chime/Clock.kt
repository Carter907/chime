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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Player1Pad(modifier: Modifier = Modifier) {


    var player1Time by remember { mutableStateOf(0f) }
    Box(
        modifier = modifier,
    ) {
        Text(fontSize = 100.sp, text = "$player1Time", modifier = Modifier.align(Alignment.Center));
    }
}

@Composable
fun Player2Pad(modifier: Modifier = Modifier) {

    var player2Time by remember { mutableStateOf(0f) }
    Box(
        modifier = modifier
    ) {
        Text(fontSize = 100.sp, text = "$player2Time", modifier = Modifier.align(Alignment.Center));
    }
}

@Composable
fun MiddleClockButtons(modifier: Modifier = Modifier) {
   Row(
       modifier = Modifier.fillMaxWidth(),
       horizontalArrangement = Arrangement.SpaceEvenly,
   ) {
       Button(onClick = {}) {
            Icon(Icons.Rounded.Refresh, "reset")
       }
       Button(onClick = {}) {
           Icon(Icons.Rounded.Edit, "edit")
       }

   }
}


@Composable
fun Clock(onScreenChange: (Screen) -> Unit, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Player1Pad(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray, shape = ShapeDefaults.Large)
            .weight(1f)
            .clickable { });
        MiddleClockButtons()

        Player2Pad(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray, shape = ShapeDefaults.Large)
            .weight(1f)
            .clickable { });

    }
}
