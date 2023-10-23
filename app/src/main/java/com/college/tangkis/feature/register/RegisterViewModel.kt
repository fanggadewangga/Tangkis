package com.college.tangkis.feature.register

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.college.tangkis.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    val studentNumberState = mutableStateOf("")
    val isStudentNumberFieldClicked = mutableStateOf(false)
    val isValidStudentNumber = derivedStateOf {
        studentNumberState.value.isEmpty() && isStudentNumberFieldClicked.value
    }

    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isValidName = derivedStateOf {
        studentNumberState.value.isEmpty() && isStudentNumberFieldClicked.value
    }

    val passwordState = mutableStateOf("")
    val isValidPasswordState = derivedStateOf {
        passwordState.value.isNotEmpty() && passwordState.value.length < 8
    }

    val passwordConfirmState = mutableStateOf("")
    val isValidConfirmPasswordState = derivedStateOf {
        passwordConfirmState.value.isNotEmpty() && (passwordState.value != passwordConfirmState.value)
    }
}