package com.college.tangkis.feature.register

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.user.UserRegisterRequest
import com.college.tangkis.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isValidName = derivedStateOf {
        studentNumberState.value.isEmpty() && isStudentNumberFieldClicked.value
    }

    val studentNumberState = mutableStateOf("")
    val isStudentNumberFieldClicked = mutableStateOf(false)
    val isValidStudentNumber = derivedStateOf {
        studentNumberState.value.isEmpty() && isStudentNumberFieldClicked.value
    }

    val phoneNumber = mutableStateOf("")
    val isPhoneNumberFieldClicked = mutableStateOf(false)
    val isValidPhoneNumber = derivedStateOf {
        phoneNumber.value.isEmpty() && isPhoneNumberFieldClicked.value
    }

    val passwordState = mutableStateOf("")
    val isValidPasswordState = derivedStateOf {
        passwordState.value.isNotEmpty() && passwordState.value.length < 8
    }

    val passwordConfirmState = mutableStateOf("")
    val isValidConfirmPasswordState = derivedStateOf {
        passwordConfirmState.value.isNotEmpty() && (passwordState.value != passwordConfirmState.value)
    }

    val isChecked = mutableStateOf(false)

    private val _registerState = MutableStateFlow<Resource<String>>(Resource.Empty())
    val registerState = _registerState.asStateFlow()

    fun register() {
        viewModelScope.launch {
            val name = nameState.value
            val nim = studentNumberState.value
            val whatsapp = "+62${phoneNumber.value}"
            val password = passwordState.value
            val body = UserRegisterRequest(name, nim, whatsapp, password)
            repository.register(body).collect {
                _registerState.value = it
            }
        }
    }
}