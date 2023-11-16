package com.college.tangkis.feature.report

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.report.ReportRepository
import com.college.tangkis.domain.model.report.ReportDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportDetailViewModel @Inject constructor(private val reportRepository: ReportRepository): ViewModel() {
    val isError = mutableStateOf(true)

    private val _reportDetailState = MutableStateFlow<Resource<ReportDetail>>(Resource.Loading())
    val reportDetailState = _reportDetailState.asStateFlow()

    fun getReportDetail(reportId: String = "") {
        viewModelScope.launch {
            reportRepository.getReportDetail(reportId).collect {
                _reportDetailState.value = it
            }
        }
    }
}