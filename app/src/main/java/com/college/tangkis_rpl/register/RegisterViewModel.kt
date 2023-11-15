package com.college.tangkis_rpl.register

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.login.LoginActivity
import com.college.tangkis_rpl.model.AuthHandler
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    fun register(nama: String, nim: String, password: String, activity: RegisterActivity) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.register(nama, nim, password)
            val intent = Intent(activity, LoginActivity::class.java)
            if (errorMessage != "")
                activity.showErrorMessage(errorMessage)
            else
                activity.startActivity(intent)
        }
    }
}