package com.college.tangkis_rpl.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.AuthHandler
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun getUser(nim: String, password: String, activity: LoginActivity) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.getUser(nim, password)
            if (errorMessage != "")
                activity.showAlert(errorMessage)
            else
                activity.showHomepage()
        }
    }

    fun checkLoginStatus(activity: LoginActivity) {
        val authHandler = AuthHandler()
        val isLogin = authHandler.checkLoginStatus()
        if (isLogin)
            activity.showHomepage()
    }
}