package com.example.kotlin.screen.login

interface LoginNavigator {
    fun onFailure(message: String)
    fun onStarted()
    fun onSuccess()
}