package com.college.tangkis.feature.consult

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.consultation.ConsultationRepository
import com.college.tangkis.domain.model.consultation.ConsultationDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultationDetailViewModel @Inject constructor(private val consultationRepository: ConsultationRepository) : ViewModel() {
    val isError = mutableStateOf(true)

    private val _consultationDetailState = MutableStateFlow<Resource<ConsultationDetail>>(Resource.Loading())
    val consultationDetailState = _consultationDetailState.asStateFlow()

    fun getConsultationDetail(consultationId: String = "") {
        viewModelScope.launch {
            consultationRepository.getConsultationDetail(consultationId).collect {
                _consultationDetailState.value = it
            }
        }
    }
}