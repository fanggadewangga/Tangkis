package com.college.tangkis.feature.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.response.user.UserResponse
import com.college.tangkis.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    val isError = mutableStateOf(false)
    val showLogoutDialog = mutableStateOf(false)

    private val _profileState = MutableStateFlow<Resource<UserResponse?>>(Resource.Loading())
    val profileState = _profileState.asStateFlow()

    private fun getProfileData() {
        viewModelScope.launch {
            userRepository.getUserDetail().collect {
                _profileState.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }

    init {
        getProfileData()
    }
}