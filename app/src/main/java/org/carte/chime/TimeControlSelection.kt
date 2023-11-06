package org.carte.chime

import android.inputmethodservice.Keyboard
import android.widget.NumberPicker
import android.widget.TimePicker
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carte.chime.model.TimeControl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeControlSelection(modifier: Modifier = Modifier, onSelectionPressed: (TimeControl) -> Unit) {

    val timeControls = listOf(
        TimeControl(1.0, 0.0), TimeControl(1.0, 1.0), TimeControl(2.0, 1.0),
        TimeControl(3.0, 0.0), TimeControl(3.0, 2.0), TimeControl(5.0, 0.0),
        TimeControl(10.0, 0.0), TimeControl(15.0, 10.0), TimeControl(30.0, 0.0)
    );
    var showCustom by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf(0.0) }
    var increment by remember { mutableStateOf(0.0) }


    if (showCustom) {

        val context = LocalContext.current
        AlertDialog(
            onDismissRequest = { showCustom = false },
            confirmButton = {
                Button(onClick = {

                    onSelectionPressed(TimeControl(time, increment))
                }) {
                    Text("confirm")
                }
            },
            title = { Text("Custom Time Control") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    var timeText by rememberSaveable { mutableStateOf("") }
                    var incrementText by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = timeText,
                        singleLine = true,
                        onValueChange = {
                            timeText = it;

                            time = if (it.isEmpty())
                                0.0;

                            else if (it.matches(Regex("0*\\.0*"))){
                                0.0;
                            } else
                                it.toDouble()
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("time (min)") }

                    )
                    TextField(
                        value = incrementText,
                        singleLine = true,
                        onValueChange = {
                            incrementText = it;

                            increment = if (it.isEmpty())
                                0.0;
                            else if (it.matches(Regex("0*\\.0*"))) {
                                0.0;
                            } else
                                it.toDouble()
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("increment (s)") }

                    )
                }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Time Control",
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(onClick = { showCustom = true }) {
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "custom time control"
                )
            }

        }
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            userScrollEnabled = false,
            columns = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Top

        ) {
            items(timeControls) {

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(1f)
                ) {
                    Button(
                        onClick = { onSelectionPressed(it) },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(it.toString(), fontSize = 30.sp, textAlign = TextAlign.Center);
                    }
                }

            }
        }


    }


}