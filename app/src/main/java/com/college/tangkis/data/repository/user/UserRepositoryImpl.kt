package com.college.tangkis.data.repository.user

import android.util.Log
import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.user.UserLoginRequest
import com.college.tangkis.data.model.request.user.UserPasswordRequest
import com.college.tangkis.data.model.request.user.UserRegisterRequest
import com.college.tangkis.data.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.model.response.token.TokenResponse
import com.college.tangkis.data.model.response.user.UserResponse
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.ApiService
import com.college.tangkis.data.source.remote.NetworkBoundRequest
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.RemoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val apiService: ApiService,
    private val remoteDataSource: RemoteDataSource
) : UserRepository {

    override suspend fun savePassedOnboardStatus(isPassed: Boolean) =
        datastore.savePassedOnboardStatus(isPassed)

    override suspend fun readPassedOnboardStatus(): Flow<Boolean> =
        datastore.readPassedOnboardStatus()

    override suspend fun saveBearerToken(token: String) = datastore.saveBearerToken(token)

    override suspend fun readBearerToken(): Flow<String> = datastore.readBearerToken()

    override suspend fun saveNIM(nim: String) = datastore.saveNIM(nim)

    override suspend fun readNIM(): Flow<String> = datastore.readNIM()

    override suspend fun deleteToken() = datastore.deleteToken()

    override suspend fun deleteNIM() = datastore.deleteNIM()

    override suspend fun login(body: UserLoginRequest): Flow<Resource<Unit>> = object : NetworkBoundRequest<TokenResponse?>() {
        override suspend fun createCall(): Flow<RemoteResponse<TokenResponse?>> = remoteDataSource.login(body)
        override suspend fun saveCallResult(data: TokenResponse?) {
            if (data != null) {
                datastore.saveBearerToken(data.token)
                datastore.saveNIM(body.nim)
            }
        }
    }.asFlow()

    override suspend fun register(body: UserRegisterRequest): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.register(body)
            if (response.error) {
                Log.d("Register", response.message)
                emit(Resource.Error(response.message))
            } else {
                emit(Resource.Success(response.message))
                Log.d("Token", response.data!!.token)
                saveBearerToken(response.data.token)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Register", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUserDetail(): Flow<Resource<UserResponse?>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${readBearerToken().first()}"
            val nim = readNIM().first()
            Log.d("User Detail Token", token)
            val response = apiService.getUserDetail(token, nim)
            if (response.error) {
                Log.d("User Detail", response.data.toString())
                emit(Resource.Error(response.message))
            } else {
                response.message.let { Log.d("UserName", it) }
                emit(Resource.Success(response.data))
            }
        } catch (e: Exception) {
            Log.d("User Detail", e.message.toString())
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun changePassword(body: UserPasswordRequest): Flow<Resource<String>> =
        flow<Resource<String>> {
            emit(Resource.Loading())
            try {
                val token = "Bearer ${readBearerToken().first()}"
                val nim = readNIM().first()
                val response = apiService.changePassword(token, nim, body)
                if (response.error) {
                    Log.d("Change Password", response.message)
                    emit(Resource.Error(response.message))
                } else {
                    emit(Resource.Success(response.data!!))
                    Log.d("Change Pass", response.message)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Change Password", e.message.toString())
                emit(Resource.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun changeWhatsapp(newWhatsapp: UserWhatsappRequest): Flow<Resource<String>> =
        flow<Resource<String>> {
            emit(Resource.Loading())
            try {
                val token = "Bearer ${readBearerToken().first()}"
                val nim = readNIM().first()
                val response = apiService.changeWhatsapp(token, nim, newWhatsapp)
                if (response.error) {
                    Log.d("Change WA", response.message)
                    emit(Resource.Error(response.message))
                } else {
                    emit(Resource.Success(response.data!!))
                    Log.d("Change WA", response.message)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Change WA", e.message.toString())
                emit(Resource.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun logout(): Flow<Resource<String>> = flow<Resource<String>> {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${readBearerToken().first()}"
            val response = apiService.logout(token)
            if (response.error) {
                Log.d("Logout", response.message)
                emit(Resource.Error(response.message))
            } else {
                emit(Resource.Success(response.data!!))
                deleteToken()
                deleteNIM()
                Log.d("Logout", response.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Logout", e.message.toString())
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}