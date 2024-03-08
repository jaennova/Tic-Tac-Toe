package com.jaennova.tictactoe.models

import androidx.annotation.DrawableRes
import com.jaennova.tictactoe.R

sealed class CellState(@DrawableRes val res: Int) {
    data object Blank : CellState(R.drawable.ic_blank)
    data object Cross : CellState(R.drawable.ic_cross)
    data object Circle : CellState(R.drawable.ic_circle)
}
