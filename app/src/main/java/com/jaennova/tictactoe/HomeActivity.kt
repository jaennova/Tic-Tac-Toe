package com.jaennova.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaennova.tictactoe.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        goToGame()
    }

    private fun goToGame() {
        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, TicTacToeGameActivity::class.java)
            startActivity(intent)
        }
    }

}