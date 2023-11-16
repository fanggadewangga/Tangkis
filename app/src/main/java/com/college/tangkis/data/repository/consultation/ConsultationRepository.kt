package com.college.tangkis.data.repository.consultation

import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.request.consultation.ConsultationRequest
import com.college.tangkis.domain.model.consultation.AddConsultation
import com.college.tangkis.domain.model.consultation.ConsultationDetail
import com.college.tangkis.domain.model.consultation.ConsultationList
import kotlinx.coroutines.flow.Flow

interface ConsultationRepository {
    suspend fun addConsultation(body: ConsultationRequest): Flow<Resource<AddConsultation>>
    suspend fun getConsultations(): Flow<Resource<List<ConsultationList>>>
    suspend fun getConsultationDetail(consultationId: String): Flow<Resource<ConsultationDetail>>
}