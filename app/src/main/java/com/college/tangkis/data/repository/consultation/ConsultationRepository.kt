package com.college.tangkis.data.repository.consultation

import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.request.consultation.ConsultationRequest
import com.college.tangkis.data.source.remote.model.response.consultation.AddConsultationResponse
import com.college.tangkis.data.source.remote.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.source.remote.model.response.consultation.ConsultationListResponse
import kotlinx.coroutines.flow.Flow

interface ConsultationRepository {
    suspend fun addConsultation(body: com.college.tangkis.data.source.remote.model.request.consultation.ConsultationRequest): Flow<Resource<com.college.tangkis.data.source.remote.model.response.consultation.AddConsultationResponse?>>
    suspend fun getConsultations(): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.consultation.ConsultationListResponse>>>
    suspend fun getConsultationDetail(consultationId: String): Flow<Resource<com.college.tangkis.data.source.remote.model.response.consultation.ConsultationDetailResponse>>
}