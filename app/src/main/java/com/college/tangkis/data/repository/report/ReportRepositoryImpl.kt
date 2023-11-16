package com.college.tangkis.data.repository.report

import android.util.Log
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.request.report.ReportRequest
import com.college.tangkis.data.source.remote.model.response.report.AddReportResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportResponse
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val apiService: ApiService,
) : ReportRepository {
    override suspend fun addReport(body: com.college.tangkis.data.source.remote.model.request.report.ReportRequest): Flow<Resource<com.college.tangkis.data.source.remote.model.response.report.AddReportResponse?>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.postReport(token, nim, body)
            if (result.error) {
                Log.d("Add Report", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Add Report", result.message)
                emit(Resource.Success(result.data))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getReports(): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.report.ReportResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                val nim = datastore.readNIM().first()
                val result = apiService.getReports(token, nim)
                if (result.error) {
                    Log.d("Get Reports", result.message)
                    emit(Resource.Error(result.message))
                } else {
                    Log.d("Get Reports", result.message)
                    emit(Resource.Success(result.data!!))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getReportDetail(reportId: String): Flow<Resource<com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse>> =
        flow {
            emit(Resource.Loading())
            try {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                val nim = datastore.readNIM().first()
                val result = apiService.getReportDetail(token, nim, reportId)
                if (result.error) {
                    Log.d("Get Report Detail", result.message)
                    emit(Resource.Error(result.message))
                } else {
                    Log.d("Get Report Detail", result.message)
                    emit(Resource.Success(result.data!!))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)
}