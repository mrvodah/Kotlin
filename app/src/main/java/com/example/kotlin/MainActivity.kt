package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num = 10
        var longValue = num.toLong()

        val value = "stringValue"

        println("Length = ${value.length}")

        val array = arrayOf("value1", "value2", "value3")

        for(value in 1..5){

        }

    }
}
