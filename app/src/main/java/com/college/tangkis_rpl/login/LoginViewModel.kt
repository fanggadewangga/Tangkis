package com.college.tangkis_rpl.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.AuthHandler
import com.college.tangkis_rpl.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    fun getUser( nim: String, password: String, activity: LoginActivity) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.getUser(nim, password)
            Log.d("Error message", errorMessage)

            if (errorMessage != "") {
                activity.showAlert(errorMessage)
            } else {
                activity.showHomepage()
            }
        }
    }
}