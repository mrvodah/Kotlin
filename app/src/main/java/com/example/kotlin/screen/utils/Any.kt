package com.example.kotlin.screen.utils

inline fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}