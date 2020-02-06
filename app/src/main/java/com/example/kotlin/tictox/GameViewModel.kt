package com.example.kotlin.tictox

import android.os.Handler
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.ViewModel
import com.example.kotlin.R

class GameViewModel: ViewModel() {
    lateinit var cells: ObservableArrayMap<String, Int>
    lateinit var game: Game

    init {
        game = Game("1", "2")
        cells = ObservableArrayMap()
    }

    fun onClickedAtCell(r: Int, c: Int){
        if(game.cells?.get(r)?.get(c) == null){
            game.cells?.get(r)?.set(c, Cell(game.currentPlayer))
            var res = 0
            if(game.currentPlayer.value == Playervalue.VALUE_X)
                res = R.drawable.ic_radio_button_unchecked_black_24dp
            else
                res = R.drawable.ic_close_black_24dp
            cells[r.toString() + c.toString()] = res

            if(game.isGameEnded())
                resetGame()
            else
                game.switchPlayer()
        }
    }

    fun resetGame() {
        val handle = Handler().postDelayed(Runnable {
            game.reset()
            cells.clear()
        }, 1000)
    }

}