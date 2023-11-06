package com.college.tangkis.feature.changepassword

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.user.UserPasswordRequest
import com.college.tangkis.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
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

    private val _changePasswordState = MutableStateFlow<Resource<String>>(Resource.Empty())
    val changePasswordState = _changePasswordState.asStateFlow()

    private val _logoutState = MutableStateFlow<Resource<String>>(Resource.Empty())
    val logoutState = _logoutState.asStateFlow()

    fun changePassword() {
        viewModelScope.launch {
            val body = UserPasswordRequest(oldPassword = currentPasswordState.value, newPassword = newPasswordState.value)
            userRepository.changePassword(body).collect {
                _changePasswordState.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout().collect {
                _logoutState.value = it
            }
        }
    }
}