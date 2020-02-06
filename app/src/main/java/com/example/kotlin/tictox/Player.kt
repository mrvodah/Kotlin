package com.example.kotlin.tictox

class Player(var name: String, var valueObject: String) {

    lateinit var value: Playervalue

    init {
        value = if(valueObject.equals("X"))
            Playervalue.VALUE_X
        else if(valueObject.equals("O"))
            Playervalue.VALUE_O
        else
            Playervalue.VALUE_EMPTY

    }

}