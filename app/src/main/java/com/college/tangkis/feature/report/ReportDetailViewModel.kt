package com.college.tangkis.feature.report

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse
import com.college.tangkis.data.repository.report.ReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportDetailViewModel @Inject constructor(private val reportRepository: ReportRepository): ViewModel() {
    val isError = mutableStateOf(true)

    private val _reportDetailState = MutableStateFlow<Resource<com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse>>(Resource.Loading())
    val reportDetailState = _reportDetailState.asStateFlow()

    fun getReportDetail(reportId: String = "") {
        viewModelScope.launch {
            if (reportId.isEmpty())
                reportRepository.getReportDetail("REPORT-WzsdRlvz38mJdN2rhcqoD").collect {
                    _reportDetailState.value = it
                }
            else
                reportRepository.getReportDetail(reportId).collect {
                    _reportDetailState.value = it
                }
        }
    }
}