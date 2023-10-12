package com.college.tangkis.feature.onboard

import androidx.lifecycle.ViewModel
import com.college.tangkis.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    suspend fun savePassedOnboardStatus() = repository.savePassedOnboardStatus(true)
}