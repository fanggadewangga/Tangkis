package com.college.tangkis.data.repository.consultation

import android.util.Log
import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.consultation.ConsultationRequest
import com.college.tangkis.data.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.model.response.consultation.ConsultationListResponse
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ConsultationRepositoryImpl @Inject constructor(private val datastore: TangkisDatastore, private val apiService: ApiService): ConsultationRepository {
    override suspend fun addConsultation(body: ConsultationRequest): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.postConsultation(token, nim, body)
            if (result.error) {
                Log.d("Add Consultation", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Add Consultation", result.message)
                emit(Resource.Success(result.message))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getConsultations(): Flow<Resource<List<ConsultationListResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.getConsultations(token, nim)
            if (result.error) {
                Log.d("Get Consultation", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Get Consultation", result.message)
                emit(Resource.Success(result.data!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getConsultationDetail(consultationId: String): Flow<Resource<ConsultationDetailResponse>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.getConsultationDetail(token, nim, consultationId)
            if (result.error) {
                Log.d("Get Consultation Detail", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Get Consultation Detail", result.message)
                emit(Resource.Success(result.data!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}