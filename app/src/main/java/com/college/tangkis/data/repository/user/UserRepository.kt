package com.college.tangkis.data.repository.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun savePassedOnboardStatus(isPassed: Boolean)
    suspend fun readPassedOnboardStatus(): Flow<Boolean>
}