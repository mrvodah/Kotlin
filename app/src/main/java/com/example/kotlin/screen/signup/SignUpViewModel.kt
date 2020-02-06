package com.example.kotlin.screen.signup

import androidx.lifecycle.ViewModel
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.screen.utils.rx.BaseSchedulerProvider
import com.example.kotlin.screen.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SignUpViewModel(private val userRepository: UserRepository): ViewModel() {
    private val baseSchedulerProvider: BaseSchedulerProvider by lazy { SchedulerProvider.instance }
    private var signUpNavigator: SignUpNavigator? = null
    private val disposables = CompositeDisposable()

    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    fun setNavigator(navigator: SignUpNavigator) {
        signUpNavigator = navigator
    }

    fun userRegister() {
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            signUpNavigator?.onFailure("You must enter your email and password")
            return
        }

        if (password!!.length < 8) {
            signUpNavigator?.onFailure("Password not enough character")
            return
        }

        if (!confirmPassword.equals(password)) {
            signUpNavigator?.onFailure("Your password not match")
            return
        }

        signUpNavigator?.onStarted()
        val disposable = userRepository.register(email!!, password!!)
            .subscribeOn(baseSchedulerProvider.io())
            .observeOn(baseSchedulerProvider.ui())
            .subscribe ({
                signUpNavigator?.onSuccess()
            }, {
                signUpNavigator?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

}