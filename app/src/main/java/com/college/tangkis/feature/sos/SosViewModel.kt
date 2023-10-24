package com.college.tangkis.feature.sos

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SosViewModel @Inject constructor(): ViewModel() {
    val isSending = mutableStateOf(false)
    val isInRange = mutableStateOf(false)
    val timeLeft = mutableIntStateOf(5)
}