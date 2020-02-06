package com.example.kotlin.screen.signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.kotlin.R
import com.example.kotlin.databinding.ActivitySignUpBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), SignUpNavigator, KodeinAware {

    override val kodein by kodein()
    private lateinit var viewDataBinding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel
    private val factory: SignUpViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewDataBinding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java)
        viewDataBinding.viewModel = viewModel
        viewModel.setNavigator(this)
    }

    override fun onStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
        finish()
    }

    override fun onFailure(message: String) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Cancel", null)
        alertDialog.setNegativeButton("Ok", null)
        alertDialog.show()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SignUpActivity::class.java)
    }

}
