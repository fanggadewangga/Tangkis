package com.college.tangkis_rpl.register

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.login.LoginPage
import com.college.tangkis_rpl.model.AuthHandler
import kotlinx.coroutines.launch

class RegisterControl : ViewModel() {

    fun register(nama: String, nim: String, password: String, activity: RegisterPage) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.register(nama, nim, password)
            val intent = Intent(activity, LoginPage::class.java)
            if (errorMessage != "")
                activity.showAlert(errorMessage)
            else
                activity.startActivity(intent)
        }
    }

    fun showRegisterPage(activity: LoginPage) {
        val intent = Intent(activity, RegisterPage::class.java)
        activity.startActivity(intent)
    }
}