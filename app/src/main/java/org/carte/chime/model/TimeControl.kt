package org.carte.chime.model

data class TimeControl(val time: Double, val increment: Double) {
    override fun toString(): String {
        if (increment == 0.0)
            return "${time.toInt()} min"

        return "${time.toInt()} | ${increment.toInt()}"
    }
}