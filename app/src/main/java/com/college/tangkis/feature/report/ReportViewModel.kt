package com.college.tangkis.feature.report

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.report.ReportRepository
import com.college.tangkis.data.repository.user.UserRepository
import com.college.tangkis.data.source.remote.model.response.report.AddReportResponse
import com.college.tangkis.domain.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val reportRepository: ReportRepository,
) : ViewModel() {
    val screenIndex = mutableIntStateOf(1)
    val story = mutableStateOf("")
    val isNeedAccompaniment = mutableStateOf(true)
    val accompanimentTypeIndex = mutableIntStateOf(1)
    val isDropdownExpanded = mutableStateOf(false)
    val selectedTimeIndex = mutableIntStateOf(100)
    val selectedTimeId = mutableStateOf("")
    val dateState = mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.O)
    val pickedDate = mutableStateOf(LocalDate.now())

    @RequiresApi(Build.VERSION_CODES.O)
    val formattedDate = derivedStateOf {
        DateTimeFormatter.ofPattern("MM/dd/yyy").format(pickedDate.value)
    }

    private val _userState = MutableStateFlow<Resource<User>>(Resource.Loading())
    val userState = _userState.asStateFlow()

    private val _reportState = MutableStateFlow<Resource<AddReportResponse?>>(Resource.Empty())
    val reportState = _reportState.asStateFlow()

    fun hideDropdown() = run { isDropdownExpanded.value = false }

    private fun getUserData() {
        viewModelScope.launch {
            userRepository.getUserDetail().collect {
                _userState.value = it
            }
        }
    }

    @SuppressLint("NewApi")
    fun sentReport() {
        viewModelScope.launch {
            var consultationBody: com.college.tangkis.data.source.remote.model.request.consultation.ConsultationRequest? = null
            if (isNeedAccompaniment.value) {
                consultationBody =
                    com.college.tangkis.data.source.remote.model.request.consultation.ConsultationRequest(
                        story = story.value,
                        consultationType = accompanimentTypeIndex.intValue,
                        date = formattedDate.value,
                        time = selectedTimeId.value
                    )
            }
            val reportBody =
                com.college.tangkis.data.source.remote.model.request.report.ReportRequest(
                    story = story.value,
                    isNeedConsultation = isNeedAccompaniment.value,
                    consultation = consultationBody
                )
            reportRepository.addReport(body = reportBody).collect {
                _reportState.value = it
            }
        }
    }

    init {
        getUserData()
    }
}