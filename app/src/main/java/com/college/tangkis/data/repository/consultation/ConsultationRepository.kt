package com.college.tangkis.data.repository.consultation

import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.consultation.ConsultationRequest
import com.college.tangkis.data.model.response.consultation.AddConsultationResponse
import com.college.tangkis.data.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.model.response.consultation.ConsultationListResponse
import kotlinx.coroutines.flow.Flow

interface ConsultationRepository {
    suspend fun addConsultation(body: ConsultationRequest): Flow<Resource<AddConsultationResponse?>>
    suspend fun getConsultations(): Flow<Resource<List<ConsultationListResponse>>>
    suspend fun getConsultationDetail(consultationId: String): Flow<Resource<ConsultationDetailResponse>>
}