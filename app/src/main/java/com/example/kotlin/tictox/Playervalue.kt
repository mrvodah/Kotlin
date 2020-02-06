package com.example.kotlin.tictox

enum class Playervalue(var value: String) {
    VALUE_EMPTY("VALUE_EMPTY"),
    VALUE_X("VALUE_X"),
    VALUE_O("VALUE_O");

    override fun toString(): String{
        return if(value.equals("VALUE_X"))
            "X"
        else
            "O"
    }


}