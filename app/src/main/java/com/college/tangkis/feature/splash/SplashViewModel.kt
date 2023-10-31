package com.college.tangkis.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val isPassedOnboard = MutableStateFlow(false)
    val token = MutableStateFlow("")
    private fun readHaveRunAppBefore() = viewModelScope.launch {
        repository.readPassedOnboardStatus().collect {
            isPassedOnboard.value = it
        }
    }

    private fun readToken() = viewModelScope.launch {
        repository.readBearerToken().collect {
            token.value = it
        }
    }

    init {
        readToken()
        readHaveRunAppBefore()
    }
}