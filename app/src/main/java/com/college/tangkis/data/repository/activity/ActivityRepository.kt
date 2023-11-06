package com.college.tangkis.data.repository.activity

import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.response.activity.ActivityResponse
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    suspend fun getInProgressActivity(): Flow<Resource<List<ActivityResponse>>>
    suspend fun getHistoryActivity(): Flow<Resource<List<ActivityResponse>>>
}