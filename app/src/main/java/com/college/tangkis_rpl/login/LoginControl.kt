package com.college.tangkis_rpl.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.MainActivity
import com.college.tangkis_rpl.model.AuthHandler
import com.college.tangkis_rpl.register.RegisterPage
import kotlinx.coroutines.launch

class LoginControl : ViewModel() {

    fun getUser(nim: String, password: String, activity: LoginPage) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.getUser(nim, password)
            val intent = makeIntent(activity, MainActivity::class.java)
            if (errorMessage != "")
                activity.showAlert(errorMessage)
            else
                activity.startActivity(intent)
        }
    }

    fun checkLoginStatus(activity: LoginPage) {
        val authHandler = AuthHandler()
        val isLogin = authHandler.checkLoginStatus()
        val intent = makeIntent(activity, MainActivity::class.java)
        if (isLogin)
            activity.startActivity(intent)
    }

    private fun makeIntent(context: android.content.Context, target: Class<MainActivity>): Intent {
        return Intent(context, target)
    }
}