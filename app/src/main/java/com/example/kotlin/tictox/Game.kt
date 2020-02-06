package com.example.kotlin.tictox

class Game(var playerOneName: String, var playerTwoName: String) {

    lateinit var player1: Player
    lateinit var player2: Player

    lateinit var currentPlayer: Player

    var winners: Player? = null

    var cells: Array<Array<Cell>>? = null

    init {
//        cells = arrayOf(Array(3, ), Array(3))
        cells = Array(3) {
            Array(3) {
                Cell(null)
            }
        }
        player1 = Player(playerOneName, "X")
        player2 = Player(playerTwoName, "O")
        currentPlayer = player1
    }

    fun switchPlayer(){
        currentPlayer = if(currentPlayer == player1)
            player2
        else
            player1
    }

    fun isGameEnded(): Boolean {
        if(hasThreeSameOnDiagonalCells()
            || hasThreeSameOnHorizontalCells()
            || hasThreeSameOnVerticalCells()){
            winners = currentPlayer
            return true
        }

        if(isBoardFull()){
            winners = null
            return true
        }

        return false
    }

    fun hasThreeSameOnHorizontalCells(): Boolean {
        val value = currentPlayer.value

        return areEquals(value, cells?.get(0)?.get(0), cells?.get(1)?.get(0), cells?.get(2)?.get(0))
                || areEquals(value, cells?.get(0)?.get(1), cells?.get(1)?.get(1), cells?.get(2)?.get(1))
                || areEquals(value, cells?.get(0)?.get(2), cells?.get(1)?.get(2), cells?.get(2)?.get(2))
    }

    fun hasThreeSameOnVerticalCells(): Boolean {
        val value = currentPlayer.value

        return areEquals(value, cells?.get(0)?.get(0), cells?.get(0)?.get(1), cells?.get(0)?.get(2))
                || areEquals(value, cells?.get(1)?.get(0), cells?.get(1)?.get(1), cells?.get(1)?.get(2))
                || areEquals(value, cells?.get(2)?.get(0), cells?.get(2)?.get(1), cells?.get(2)?.get(2))
    }

    fun hasThreeSameOnDiagonalCells(): Boolean {
        val value = currentPlayer.value

        return areEquals(value, cells?.get(0)?.get(0), cells?.get(1)?.get(1), cells?.get(2)?.get(2))
                || areEquals(value, cells?.get(0)?.get(2), cells?.get(1)?.get(1), cells?.get(2)?.get(0))
    }

    fun isBoardFull(): Boolean {
        for(i in 0..BOARD_SIZE){
            for(j in 0..BOARD_SIZE){
                if(cells?.get(i)?.get(j) == null || cells?.get(i)?.get(j)!!.isEmpty())
                    return false
            }
        }
        return true
    }

    fun areEquals(value: Playervalue, vararg cells: Cell?): Boolean {
        for(cell in cells){
            if(cell == null || cell.isEmpty() || cell.player != currentPlayer || cell.player!!.value != value)
                return false
        }
        return true
    }

    fun reset() {
        currentPlayer = player1
        cells = Array(3) {
            Array(3) {
                Cell(null)
            }
        }
    }

    companion object {
        val TAG = Game.javaClass.simpleName
        val BOARD_SIZE = 3
    }
}