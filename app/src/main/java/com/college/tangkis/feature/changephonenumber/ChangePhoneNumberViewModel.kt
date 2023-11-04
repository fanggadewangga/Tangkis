package com.college.tangkis.feature.changephonenumber

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ChangePhoneNumberViewModel @Inject constructor() : ViewModel() {
    val oldNumberState = mutableStateOf("")
    val newNumberState = mutableStateOf("")

    val isOldNumberFieldClicked = mutableStateOf(false)
    val isNewNumberFieldClicked = mutableStateOf(false)

    val isValidOldNumber = derivedStateOf {
        oldNumberState.value.isEmpty() && isOldNumberFieldClicked.value
    }

    val isValidNewNumber = derivedStateOf {
        newNumberState.value.isEmpty() && isNewNumberFieldClicked.value
    }
}