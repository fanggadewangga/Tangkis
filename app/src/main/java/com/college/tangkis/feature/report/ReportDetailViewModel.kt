package com.college.tangkis.feature.report

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportDetailViewModel @Inject constructor(): ViewModel() {
    val isError = mutableStateOf(true)
}