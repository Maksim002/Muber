package com.example.muber.android.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.muber.android.R
import com.example.muber.login.LoginViewMode

class LoginActivity: AppCompatActivity(R.layout.login_activity) {

    var viewModel = LoginViewMode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}