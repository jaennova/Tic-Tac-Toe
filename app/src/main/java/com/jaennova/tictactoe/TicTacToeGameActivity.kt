package com.jaennova.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jaennova.tictactoe.databinding.ActivityMainBinding
import com.jaennova.tictactoe.models.Board
import com.jaennova.tictactoe.models.BoardState
import com.jaennova.tictactoe.models.Cell

class TicTacToeGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: TicTacToeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.board.observe(this, updateBoard)
        bindClickEvents()
    }

    private val updateBoard = Observer<Board> { board ->
        binding.square0.setImageResource(board.topLeft.res)
        binding.square1.setImageResource(board.topCenter.res)
        binding.square2.setImageResource(board.topRight.res)
        binding.square3.setImageResource(board.centerLeft.res)
        binding.square4.setImageResource(board.centerCenter.res)
        binding.square5.setImageResource(board.centerRight.res)
        binding.square6.setImageResource(board.bottomLeft.res)
        binding.square7.setImageResource(board.bottomCenter.res)
        binding.square8.setImageResource(board.bottomRight.res)
        when (board.boardState) {
            BoardState.STAR_WON -> {
                setupBoard(true)
                showWinningMessage("Ganaste!")
            }

            BoardState.CIRCLE_WON -> {
                setupBoard(true)
                showWinningMessage("Te ganó la IA!")
            }

            BoardState.DRAW -> {
                setupBoard(true)
                showWinningMessage("Es un empate!")
            }

            BoardState.INCOMPLETE -> {
                setupBoard()
            }
        }
    }


    private fun setupBoard(disable: Boolean = false) {
        binding.square0.isEnabled = !disable
        binding.square1.isEnabled = !disable
        binding.square2.isEnabled = !disable
        binding.square3.isEnabled = !disable
        binding.square4.isEnabled = !disable
        binding.square5.isEnabled = !disable
        binding.square6.isEnabled = !disable
        binding.square7.isEnabled = !disable
        binding.square8.isEnabled = !disable

        binding.square0.alpha = if (disable) 0.5f else 1f
        binding.square1.alpha = if (disable) 0.5f else 1f
        binding.square2.alpha = if (disable) 0.5f else 1f
        binding.square3.alpha = if (disable) 0.5f else 1f
        binding.square4.alpha = if (disable) 0.5f else 1f
        binding.square5.alpha = if (disable) 0.5f else 1f
        binding.square6.alpha = if (disable) 0.5f else 1f
        binding.square7.alpha = if (disable) 0.5f else 1f
        binding.square8.alpha = if (disable) 0.5f else 1f
    }

    private fun bindClickEvents() {
        binding.square0.setOnClickListener { vm.boardClicked(Cell.TOP_LEFT) }
        binding.square1.setOnClickListener { vm.boardClicked(Cell.TOP_CENTER) }
        binding.square2.setOnClickListener { vm.boardClicked(Cell.TOP_RIGHT) }
        binding.square3.setOnClickListener { vm.boardClicked(Cell.CENTER_LEFT) }
        binding.square4.setOnClickListener { vm.boardClicked(Cell.CENTER_CENTER) }
        binding.square5.setOnClickListener { vm.boardClicked(Cell.CENTER_RIGHT) }
        binding.square6.setOnClickListener { vm.boardClicked(Cell.BOTTOM_LEFT) }
        binding.square7.setOnClickListener { vm.boardClicked(Cell.BOTTOM_CENTER) }
        binding.square8.setOnClickListener { vm.boardClicked(Cell.BOTTOM_RIGHT) }
        binding.buttonReset.setOnClickListener { vm.resetBoard() }
    }

    private fun showWinningMessage(message: String) {
        val alertDialogBuilder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Juego Terminado")
        alertDialogBuilder.setMessage(message)

        // Add a button to restart the game
        alertDialogBuilder.setPositiveButton("Reiniciar") { dialog, _ ->
            // Code to restart the game
            vm.resetBoard()
            dialog.dismiss()
        }

        // Add a button to return to the main screen
        alertDialogBuilder.setNegativeButton("Menu Principal") { dialog, _ ->
            // Code to return to the main screen
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }


}