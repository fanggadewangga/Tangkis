package com.college.tangkis_rpl.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.college.tangkis_rpl.model.AuthHandler

class RegisterViewModel : ViewModel() {

    fun register(nama: String, nim: String, password: String, activity: RegisterActivity) {
        val authHandler = AuthHandler()
        val errorMessage = authHandler.register(nama, nim, password)
        Log.d("Error message", errorMessage.toString())
        if (errorMessage != "")
            activity.showAlreadyRegisteredMessage()
        else
            activity.show()
    }
}