package com.college.tangkis.feature.changephonenumber

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.user.UserRepository
import com.college.tangkis.data.source.remote.api.model.request.user.UserWhatsappRequest
import com.college.tangkis.domain.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePhoneNumberViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    val newNumberState = mutableStateOf("")
    val isNewNumberFieldClicked = mutableStateOf(false)
    val isValidNewNumber = derivedStateOf {
        newNumberState.value.isEmpty() && isNewNumberFieldClicked.value
    }

    private val _userState = MutableStateFlow<Resource<User>>(Resource.Loading())
    val userState = _userState.asStateFlow()

    private val _changeWhatsappState = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    val changeWhatsappState = _changeWhatsappState.asStateFlow()

    private fun getUserData() {
        viewModelScope.launch {
            userRepository.getUserDetail().collect {
                _userState.value = it
            }
        }
    }

    fun changeWhatsapp() {
        viewModelScope.launch {
            val body =
                UserWhatsappRequest(
                    whatsapp = newNumberState.value
                )
            userRepository.changeWhatsapp(body).collect {
                _changeWhatsappState.value = it
            }
        }
    }

    init {
        getUserData()
    }
}