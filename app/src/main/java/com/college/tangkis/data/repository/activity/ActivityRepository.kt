package com.college.tangkis.data.repository.activity

import com.college.tangkis.data.Resource
import com.college.tangkis.domain.model.activity.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    suspend fun getInProgressActivity(): Flow<Resource<List<Activity>>>
    suspend fun getHistoryActivity(): Flow<Resource<List<Activity>>>
}