package com.example.kotlin.screen.signup

interface SignUpNavigator {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}