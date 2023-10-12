package com.college.tangkis.data.repository.user

import com.college.tangkis.data.source.local.TangkisDatastore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val datastore: TangkisDatastore): UserRepository {
    override suspend fun savePassedOnboardStatus(isPassed: Boolean) = datastore.savePassedOnboardStatus(isPassed)

    override suspend fun readPassedOnboardStatus(): Flow<Boolean> = datastore.readPassedOnboardStatus()
}