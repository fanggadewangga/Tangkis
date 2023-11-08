package com.college.tangkis_rpl.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis_rpl.model.AuthHandler
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    fun register(nama: String, nim: String, password: String, activity: RegisterActivity) {
        val authHandler = AuthHandler()
        viewModelScope.launch {
            val errorMessage = authHandler.register(nama, nim, password)
            if (errorMessage != "")
                activity.showErrorMessage(errorMessage)
            else
                activity.show()
        }
    }
}