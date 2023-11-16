package com.college.tangkis.data.repository.activity

import com.college.tangkis.core.base.NetworkOnlyResource
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.RemoteResponse
import com.college.tangkis.data.source.remote.api.model.response.activity.ActivityResponse
import com.college.tangkis.domain.model.activity.Activity
import com.college.tangkis.util.toActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val remoteDataSource: RemoteDataSource
) : ActivityRepository {

    override suspend fun getInProgressActivity(): Flow<Resource<List<Activity>>> = object : NetworkOnlyResource<List<Activity>, List<ActivityResponse>?>() {
        override suspend fun createCall(): Flow<RemoteResponse<List<ActivityResponse>?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.getInProgressActivity(token, nim)
        }
        override fun mapTransform(data: List<ActivityResponse>?): List<Activity> = data!!.map { it.toActivity() }
    }.asFlow()

    override suspend fun getHistoryActivity(): Flow<Resource<List<Activity>>> = object : NetworkOnlyResource<List<Activity>, List<ActivityResponse>?>() {
        override suspend fun createCall(): Flow<RemoteResponse<List<ActivityResponse>?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.getHistoryActivity(token, nim)
        }
        override fun mapTransform(data: List<ActivityResponse>?): List<Activity> = data!!.map { it.toActivity() }
    }.asFlow()
}