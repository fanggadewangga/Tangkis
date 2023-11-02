package com.college.tangkis.feature.consult

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultationDetailViewModel @Inject constructor() : ViewModel() {
    val isError = mutableStateOf(true)
}