package org.carte.chime

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

enum class Screen {

    TITLE {
        @Composable
        override fun display(modifier: Modifier, onScreenChange: (Screen) -> Unit) {
            Title(onScreenChange);
        }


    },
    CLOCK {
        @Composable
        override fun display(modifier: Modifier, onScreenChange: (Screen) -> Unit) {
            Clock(onScreenChange);
        }

    };
    @Composable
    abstract fun display(modifier: Modifier, onScreenChange: (Screen) -> Unit);
}