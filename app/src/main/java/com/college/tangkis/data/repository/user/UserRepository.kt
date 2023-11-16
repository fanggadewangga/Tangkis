package com.college.tangkis.data.repository.user

import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.request.user.UserLoginRequest
import com.college.tangkis.data.source.remote.model.request.user.UserPasswordRequest
import com.college.tangkis.data.source.remote.model.request.user.UserRegisterRequest
import com.college.tangkis.data.source.remote.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.source.remote.model.response.user.UserResponse
import com.college.tangkis.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun savePassedOnboardStatus(isPassed: Boolean)
    suspend fun readPassedOnboardStatus(): Flow<Boolean>
    suspend fun saveBearerToken(token: String)
    suspend fun readBearerToken(): Flow<String>
    suspend fun saveNIM(nim: String)
    suspend fun readNIM(): Flow<String>
    suspend fun deleteToken()
    suspend fun deleteNIM()
    suspend fun login(body: UserLoginRequest): Flow<Resource<Unit>>
    suspend fun register(body: UserRegisterRequest): Flow<Resource<Unit>>
    suspend fun getUserDetail(): Flow<Resource<User>>
    suspend fun changePassword(body: UserPasswordRequest): Flow<Resource<Unit>>
    suspend fun changeWhatsapp(newWhatsapp: UserWhatsappRequest): Flow<Resource<Unit>>
    suspend fun logout(): Flow<Resource<Unit>>
}