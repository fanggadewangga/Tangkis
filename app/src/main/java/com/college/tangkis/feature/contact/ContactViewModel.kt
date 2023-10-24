package com.college.tangkis.feature.contact

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(): ViewModel() {
    val isEmpty = mutableStateOf(false)
    val showDialog = mutableStateOf(false)
    val deleteContactName = mutableStateOf("")
}