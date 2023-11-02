package org.carte.chime.model

data class TimeControl(val time: Int, val increment: Int) {
    override fun toString(): String {
        if (increment == 0)
            return "$time min"

        return "$time | $increment"
    }
}