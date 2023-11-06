package com.college.tangkis.data.repository.user

import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.user.User
import com.college.tangkis.data.model.request.user.UserLoginRequest
import com.college.tangkis.data.model.request.user.UserPasswordRequest
import com.college.tangkis.data.model.request.user.UserRegisterRequest
import com.college.tangkis.data.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.model.response.user.UserResponse
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
    suspend fun login(body: UserLoginRequest): Flow<Resource<String>>
    suspend fun register(body: UserRegisterRequest): Flow<Resource<String>>
    suspend fun getUserDetail(): Flow<Resource<UserResponse?>>
    suspend fun changePassword(body: UserPasswordRequest): Flow<Resource<String>>
    suspend fun changeWhatsapp(newWhatsapp: UserWhatsappRequest): Flow<Resource<String>>
    suspend fun logout(): Flow<Resource<String>>
}