package com.college.tangkis_rpl.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.MainActivity
import com.college.tangkis_rpl.model.AuthHandler
import com.college.tangkis_rpl.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun getUser(nim: String, password: String, activity: LoginActivity) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.getUser(nim, password)
            val intent = Intent(activity, MainActivity::class.java)
            if (errorMessage != "")
                activity.showAlert(errorMessage)
            else
                activity.startActivity(intent)
        }
    }

    fun checkLoginStatus(activity: LoginActivity) {
        val authHandler = AuthHandler()
        val isLogin = authHandler.checkLoginStatus()
        val intent = Intent(activity, MainActivity::class.java)
        if (isLogin)
            activity.startActivity(intent)
    }

    fun showRegisterPage(activity: LoginActivity) {
        val intent = Intent(activity, RegisterActivity::class.java)
        activity.startActivity(intent)
    }
}