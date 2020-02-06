package com.example.kotlin.screen.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.kotlin.MainActivity
import com.example.kotlin.R
import com.example.kotlin.databinding.ActivityLoginBinding
import com.example.kotlin.screen.signup.SignUpActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), LoginNavigator, KodeinAware {
    override val kodein by kodein()
    private val factory: LoginViewModelFactory by instance()
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewDataBinding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        viewDataBinding.viewModel = viewModel
        viewModel.setNavigator(this)
        registerEvent()
    }

    private fun registerEvent() {
        textRegister.setOnClickListener {
            startActivity(SignUpActivity.getIntent(this))
        }

        imageFacebook.setOnClickListener {

        }

        imageGoogle.setOnClickListener {

        }
    }

    override fun onFailure(message: String) {
        buttonLogin.isEnabled = true
        progressLogin.visibility = View.GONE
        Toast.makeText(this, getString(R.string.wrong_mail_or_password), Toast.LENGTH_SHORT).show()
    }

    override fun onStarted() {
        progressLogin.visibility = View.VISIBLE
        buttonLogin.isEnabled = false
    }

    override fun onSuccess() {
        progressLogin.visibility = View.GONE
//        startActivity(MainActivity.getInstance(this))
        finish()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

}
