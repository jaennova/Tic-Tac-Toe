package com.jaennova.tictactoe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaennova.tictactoe.models.BoardState
import com.jaennova.tictactoe.models.Cell
import com.jaennova.tictactoe.models.Board
import com.jaennova.tictactoe.models.CellState
import com.jaennova.tictactoe.models.DifficultyLevel

class TicTacToeViewModel : ViewModel() {
    private val mainBoard = Board()
    val board = MutableLiveData(mainBoard)

    private fun updateBoard() {
        board.value = mainBoard
    }

    fun boardClicked(cell: Cell) {
        if (mainBoard.setCell(cell, CellState.Star)) {
            updateBoard()
            if (mainBoard.boardState == BoardState.INCOMPLETE) {
                aiTurn()
            }
        }
    }

    private fun aiTurn() {
        val circleWinningCell = mainBoard.findNextWinningMove(CellState.Circle)
        val startWinningCell = mainBoard.findNextWinningMove(CellState.Star)
        when {
            // If the AI can win, place a circle in that spot
            circleWinningCell != null -> mainBoard.setCell(circleWinningCell, CellState.Circle)
            // If the AI is about to lose, place a circle in a blocking spot
            startWinningCell != null -> mainBoard.setCell(startWinningCell, CellState.Circle)
            // Prioritize the middle
            mainBoard.setCell(Cell.CENTER_CENTER, CellState.Circle) -> Unit
            // Otherwise place a circle in a random spot
            else -> do {
                val nextCell = Cell.entries.toTypedArray().random()
                val placeSuccess = mainBoard.setCell(nextCell, CellState.Circle)
            } while (!placeSuccess)
        }

        updateBoard()
    }

    private fun aiTurnLevel(difficultyLevel: DifficultyLevel) {
        when (difficultyLevel) {
            DifficultyLevel.EASY -> {
                // Implementa la lógica para el nivel fácil
            }
            DifficultyLevel.MEDIUM -> {
                // Implementa la lógica para el nivel medio
            }
            DifficultyLevel.HARD -> {
                // Implementa la lógica para el nivel difícil
            }
        }
    }


    fun resetBoard() {
        mainBoard.clearBoard()
        updateBoard()
    }
}