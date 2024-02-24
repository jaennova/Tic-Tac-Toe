package com.jaennova.tictactoe.models

// Define una clase para representar cada elemento del Spinner
data class Difficulty(val name: String, val winPercentage: Int, val losePercentage: Int)

val itemsDifficult = listOf(
    Difficulty("Fácil", 60, 40),
    Difficulty("Medio", 50, 50),
    Difficulty("Difícil", 40, 60)
)