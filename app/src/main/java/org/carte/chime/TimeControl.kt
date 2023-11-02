package org.carte.chime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun TimeControl(modifier: Modifier = Modifier) {

    val timeControls = listOf(
        "1 min", "1 | 1", "2 | 1",
        "3 min", "3 | 2", "5 min",
        "10 min", "15 | 10", "30 min"
    );

    LazyHorizontalGrid(verticalArrangement = Arrangement.Center,
        rows = GridCells.Fixed(3)) {
        items(timeControls) {
            Box {
                Button(onClick = {}) {
                    Text(it);
                }
            }
        }
    }
}