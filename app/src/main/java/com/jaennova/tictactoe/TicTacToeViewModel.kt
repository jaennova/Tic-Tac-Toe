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
        if (mainBoard.setCell(cell, CellState.Cross)) {
            updateBoard()
            if (mainBoard.boardState == BoardState.INCOMPLETE) {
                aiTurn()
            }
        }
    }

    // Función para que la AI tome su turno
    private fun aiTurn() {
        // Busca si hay una próxima jugada ganadora para el círculo (AI)
        val circleWinningCell = mainBoard.findNextWinningMove(CellState.Circle)
        // Busca si hay una próxima jugada ganadora para la cruz (oponente)
        val startWinningCell = mainBoard.findNextWinningMove(CellState.Cross)

        // Determina qué acción tomar basada en las condiciones

        // Si la AI tiene una jugada ganadora, coloca un círculo en esa celda
        if (circleWinningCell != null) {
            mainBoard.setCell(circleWinningCell, CellState.Circle)
        }
        // Si el oponente tiene una jugada ganadora próxima, coloca un círculo en una celda para bloquear al oponente
        else if (startWinningCell != null) {
            mainBoard.setCell(startWinningCell, CellState.Circle)
        }
        // Prioriza el centro si está disponible
        else if (mainBoard.setCell(Cell.CENTER_CENTER, CellState.Circle)) {
            Unit // No es necesario hacer nada adicional
        }
        // De lo contrario, elige aleatoriamente una celda vacía y coloca un círculo en ella
        else {
            do {
                val nextCell = Cell.entries.toTypedArray().random()
                val placeSuccess = mainBoard.setCell(nextCell, CellState.Circle)
            } while (!placeSuccess)
        }

        // Actualiza el tablero después del movimiento de la AI
        updateBoard()
    }



    fun resetBoard() {
        mainBoard.clearBoard()
        updateBoard()
    }
}