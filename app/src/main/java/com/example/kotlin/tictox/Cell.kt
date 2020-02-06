package com.example.kotlin.tictox

class Cell(var player: Player?) {

    fun isEmpty() = player?.value == Playervalue.VALUE_EMPTY
}