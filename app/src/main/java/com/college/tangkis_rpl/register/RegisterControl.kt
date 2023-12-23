package com.college.tangkis_rpl.register

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.login.LoginControl
import com.college.tangkis_rpl.login.LoginPage
import com.college.tangkis_rpl.model.AuthHandler
import kotlinx.coroutines.launch

class RegisterControl : ViewModel() {

    fun register(nama: String, nim: String, password: String, activity: RegisterPage) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.register(nama, nim, password)
            val intent = makeIntentToLogin(activity, LoginPage::class.java)
            if (errorMessage != "")
                activity.showAlert(errorMessage)
            else
                activity.startActivity(intent)
        }
    }

    fun showRegisterPage(activity: LoginPage) {
        val intent = makeIntentToRegister(activity, RegisterPage::class.java)
        activity.startActivity(intent)
    }

    private fun makeIntentToLogin(context: android.content.Context, target: Class<LoginPage>): Intent {
        return Intent(context, target)
    }

    private fun makeIntentToRegister(context: android.content.Context, target: Class<RegisterPage>): Intent {
        return Intent(context, target)
    }
}