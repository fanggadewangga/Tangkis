package com.college.tangkis.feature.login

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.user.UserRepository
import com.college.tangkis.data.source.remote.api.model.request.user.UserLoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    val studentNumberState = mutableStateOf("")
    val isStudentNumberFieldClicked = mutableStateOf(false)
    val isValidStudentNumber = derivedStateOf {
        studentNumberState.value.isEmpty() && isStudentNumberFieldClicked.value
    }

    val passwordState = mutableStateOf("")
    val isValidPasswordState = derivedStateOf {
        passwordState.value.isNotEmpty() && passwordState.value.length < 8
    }

    private val _loginState = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    val loginState = _loginState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            val nim = studentNumberState.value
            val password = passwordState.value
            val body =
                UserLoginRequest(
                    nim,
                    password
                )
            repository.login(body).collect {
                _loginState.value = it
            }
        }
    }
}