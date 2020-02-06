package com.example.kotlin.screen.login

import androidx.lifecycle.ViewModel
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.screen.utils.rx.BaseSchedulerProvider
import com.example.kotlin.screen.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {
    private val baseSchedulerProvider: BaseSchedulerProvider by lazy { SchedulerProvider.instance }
    private var loginNavigator: LoginNavigator? = null
    private val disposables = CompositeDisposable()

    var email: String? = null
    var password: String? = null

    fun checkLogin() {
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            loginNavigator?.onFailure("Invalid email or password")
            return
        }
        loginNavigator?.onStarted()
        val disposable = userRepository.login(email!!, password!!)
            .subscribeOn(baseSchedulerProvider.io())
            .observeOn(baseSchedulerProvider.ui())
            .subscribe({
                loginNavigator?.onSuccess()
            }, {
                loginNavigator?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun setNavigator(loginActivity: LoginActivity) {
        this.loginNavigator = loginNavigator
    }
}