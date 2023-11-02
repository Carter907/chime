package org.carte.chime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carte.chime.model.TimeControl

@Composable
fun TimeControlSelection(modifier: Modifier = Modifier, onSelectionPressed: (TimeControl) -> Unit) {

    val timeControls = listOf(
        TimeControl(1,0), TimeControl(1,1), TimeControl(2, 1),
        TimeControl(3,0), TimeControl(3,2), TimeControl(5,0),
        TimeControl(10,0), TimeControl(15,10), TimeControl(30,0)
    );

    Text(text = "Time Control", fontSize = 35.sp, textAlign = TextAlign.Center, modifier = Modifier
        .padding(40.dp)
        .fillMaxWidth()
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().weight(1f),
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
                    Button(onClick = { onSelectionPressed(it) }, modifier = Modifier.fillMaxSize()) {
                        Text(it.toString(), fontSize = 30.sp, textAlign = TextAlign.Center);
                    }
                }

            }
        }


    }


}