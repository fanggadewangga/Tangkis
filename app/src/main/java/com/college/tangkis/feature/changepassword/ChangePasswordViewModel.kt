package com.college.tangkis.feature.changepassword

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor() : ViewModel(){
    val currentPasswordState = mutableStateOf("")

    // Logic for comparing current password to be implemented!
    val isValidCurrentPasswordState = derivedStateOf {
        currentPasswordState.value.isNotEmpty() && currentPasswordState.value.length < 8
    }

    val newPasswordState = mutableStateOf("")
    val isValidNewPasswordState = derivedStateOf {
        newPasswordState.value.isNotEmpty() && newPasswordState.value.length < 8
    }

    val passwordConfirmState = mutableStateOf("")
    val isValidConfirmPasswordState = derivedStateOf {
        passwordConfirmState.value.isNotEmpty() && (newPasswordState.value != passwordConfirmState.value)
    }
}