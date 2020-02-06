package com.example.kotlin.repository

import com.example.kotlin.data.FirebaseAuthSource

class UserRepository(
    private val firebase: FirebaseAuthSource
) {
    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}