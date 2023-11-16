package com.college.tangkis.data.repository.consultation

import com.college.tangkis.core.base.NetworkOnlyResource
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.RemoteResponse
import com.college.tangkis.data.source.remote.api.model.request.consultation.ConsultationRequest
import com.college.tangkis.data.source.remote.api.model.response.consultation.AddConsultationResponse
import com.college.tangkis.data.source.remote.api.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.source.remote.api.model.response.consultation.ConsultationListResponse
import com.college.tangkis.domain.model.consultation.AddConsultation
import com.college.tangkis.domain.model.consultation.ConsultationDetail
import com.college.tangkis.domain.model.consultation.ConsultationList
import com.college.tangkis.util.toAddConsultation
import com.college.tangkis.util.toConsultation
import com.college.tangkis.util.toConsultationList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ConsultationRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val remoteDataSource: RemoteDataSource
) : ConsultationRepository {
    override suspend fun addConsultation(body: ConsultationRequest): Flow<Resource<AddConsultation>> = object : NetworkOnlyResource<AddConsultation, AddConsultationResponse?>() {
        override suspend fun createCall(): Flow<RemoteResponse<AddConsultationResponse?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.postConsultation(token, nim, body)
        }
        override fun mapTransform(data: AddConsultationResponse?): AddConsultation = data!!.toAddConsultation()
    }.asFlow()

    override suspend fun getConsultations(): Flow<Resource<List<ConsultationList>>> = object : NetworkOnlyResource<List<ConsultationList>, List<ConsultationListResponse>?>() {
        override suspend fun createCall(): Flow<RemoteResponse<List<ConsultationListResponse>?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.getConsultation(token, nim)
        }
        override fun mapTransform(data: List<ConsultationListResponse>?): List<ConsultationList> = data!!.map { it.toConsultationList() }
    }.asFlow()

    override suspend fun getConsultationDetail(consultationId: String): Flow<Resource<ConsultationDetail>> = object : NetworkOnlyResource<ConsultationDetail, ConsultationDetailResponse?>() {
        override suspend fun createCall(): Flow<RemoteResponse<ConsultationDetailResponse?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.getConsultationDetail(token, nim, consultationId)
        }
        override fun mapTransform(data: ConsultationDetailResponse?): ConsultationDetail = data!!.toConsultation()
    }.asFlow()
}