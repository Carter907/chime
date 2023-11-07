package org.carte.chime.model

data class Player(var time: Double, var team: Team) {
}

enum class Team {
    BLACK,
    WHITE
}