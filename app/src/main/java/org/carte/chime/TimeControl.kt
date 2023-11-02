package org.carte.chime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeControl(modifier: Modifier = Modifier, onConfirmPressed: () -> Unit) {

    val timeControls = listOf(
        "1 min", "1 | 1", "2 | 1",
        "3 min", "3 | 2", "5 min",
        "10 min", "15 | 10", "30 min"
    );
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Time Control", fontSize = 30.sp, modifier = Modifier.padding(20.dp))

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = true,
            columns = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center

        ) {
            items(timeControls) {

                Box(modifier = Modifier
                    .padding(8.dp)
                    .aspectRatio(1f)
                    ) {
                    Button(onClick = {}, modifier = Modifier.fillMaxSize()) {
                        Text(it, fontSize = 20.sp, textAlign = TextAlign.Center);
                    }
                }

            }
        }


    }


}