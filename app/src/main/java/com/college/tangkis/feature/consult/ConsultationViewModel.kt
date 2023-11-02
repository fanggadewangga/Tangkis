package com.college.tangkis.feature.consult

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(): ViewModel() {
    val screenIndex = mutableIntStateOf(1)
    val story = mutableStateOf("")
    val isNeedAccompaniment = mutableStateOf(false)
    val accompanimentTypeIndex = mutableIntStateOf(100)
    val isDropdownExpanded = mutableStateOf(false)
    val selectedTimeIndex = mutableIntStateOf(100)

    fun hideDropdown() = run { isDropdownExpanded.value = false }
}