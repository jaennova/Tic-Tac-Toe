package com.jaennova.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jaennova.tictactoe.databinding.ActivityHomeBinding
import com.jaennova.tictactoe.models.Difficulty
import com.jaennova.tictactoe.models.itemsDifficult

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        selectDifficulty()
    }

    private fun selectDifficulty() {
        // Establece el valor predeterminado del Spinner
        binding.spinnerDifficult.setSelection(1)
        // Crea un adaptador personalizado para el Spinner
        val adapter = object : ArrayAdapter<Difficulty>(
            this,
            R.layout.spinner_item_difficult_layout,
            itemsDifficult
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val difficulty = itemsDifficult[position]
                view.findViewById<TextView>(R.id.textDifficulty).text = difficulty.name
                view.findViewById<TextView>(R.id.textWinLose).text =
                    "Win: ${difficulty.winPercentage}% Lose: ${difficulty.losePercentage}%"
                return view
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                return getView(position, convertView, parent)
            }
        }

// Aplica el adaptador al Spinner
        binding.spinnerDifficult.adapter = adapter
    }

}