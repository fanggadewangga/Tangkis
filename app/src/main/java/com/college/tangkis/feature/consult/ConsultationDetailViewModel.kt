package com.college.tangkis.feature.consult

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.repository.consultation.ConsultationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultationDetailViewModel @Inject constructor(private val consultationRepository: ConsultationRepository) : ViewModel() {
    val isError = mutableStateOf(true)

    private val _consultationDetailState = MutableStateFlow<Resource<com.college.tangkis.data.source.remote.model.response.consultation.ConsultationDetailResponse>>(Resource.Loading())
    val consultationDetailState = _consultationDetailState.asStateFlow()

    fun getConsultationDetail(consultationId: String = "") {
        viewModelScope.launch {
            if (consultationId.isEmpty())
                consultationRepository.getConsultationDetail("CONSULTATION-RUtFfdgzpdbLRnA1sgPBU").collect {
                    _consultationDetailState.value = it
                }
            else
                consultationRepository.getConsultationDetail(consultationId).collect {
                    _consultationDetailState.value = it
                }
        }
    }
}