package com.college.tangkis.data.repository.activity

import android.util.Log
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val apiService: ApiService,
) : ActivityRepository {
    override suspend fun getInProgressActivity(): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.getInProgressActivity(token, nim)
            if (result.error) {
                Log.d("In Progress Activity", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("In Progress Activity", result.message)
                emit(Resource.Success(result.data!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }

    override suspend fun getHistoryActivity(): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.getHistoryActivity(token, nim)
            if (result.error) {
                Log.d("History Activity", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("History Activity", result.message)
                emit(Resource.Success(result.data!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }
}