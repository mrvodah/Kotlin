package com.example.kotlin

import android.app.Application
import com.example.kotlin.data.FirebaseAuthSource
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.screen.login.LoginViewModelFactory
import com.example.kotlin.screen.signup.SignUpViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FirebaseApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))
        bind() from singleton { FirebaseAuthSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }
        bind() from provider { SignUpViewModelFactory(instance()) }
    }
}