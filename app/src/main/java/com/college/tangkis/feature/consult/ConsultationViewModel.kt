package com.college.tangkis.feature.consult

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.consultation.ConsultationRepository
import com.college.tangkis.data.repository.user.UserRepository
import com.college.tangkis.data.source.remote.api.model.request.consultation.ConsultationRequest
import com.college.tangkis.domain.model.consultation.AddConsultation
import com.college.tangkis.domain.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val consultationRepository: ConsultationRepository,
) :
    ViewModel() {
    val screenIndex = mutableIntStateOf(1)
    val story = mutableStateOf("")
    val selectedCounselorType = mutableIntStateOf(1)
    val selectedCounselingType = mutableIntStateOf(1)
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

    private val _addConsultationState = MutableStateFlow<Resource<AddConsultation>>(Resource.Empty())
    val addConsultationState = _addConsultationState.asStateFlow()

    private fun getUserData() {
        viewModelScope.launch {
            userRepository.getUserDetail().collect {
                _userState.value = it
            }
        }
    }

    fun hideDropdown() = run { isDropdownExpanded.value = false }

    @SuppressLint("NewApi")
    fun addConsultation() {
        viewModelScope.launch {
            val consultationBody =
                ConsultationRequest(
                    story = story.value,
                    counselorChoice = selectedCounselingType.intValue,
                    consultationType = selectedCounselingType.intValue,
                    date = formattedDate.value,
                    time = selectedTimeId.value
                )
            consultationRepository.addConsultation(consultationBody).collect {
                _addConsultationState.value = it
            }
        }
    }

    init {
        getUserData()
    }
}