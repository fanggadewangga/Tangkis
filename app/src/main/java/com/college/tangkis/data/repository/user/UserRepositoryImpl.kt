package com.college.tangkis.data.repository.user

import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.NetworkBoundRequest
import com.college.tangkis.data.source.remote.NetworkOnlyResource
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.RemoteResponse
import com.college.tangkis.data.source.remote.model.request.user.UserLoginRequest
import com.college.tangkis.data.source.remote.model.request.user.UserPasswordRequest
import com.college.tangkis.data.source.remote.model.request.user.UserRegisterRequest
import com.college.tangkis.data.source.remote.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.source.remote.model.response.token.TokenResponse
import com.college.tangkis.data.source.remote.model.response.user.UserResponse
import com.college.tangkis.domain.model.user.User
import com.college.tangkis.util.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val remoteDataSource: RemoteDataSource,
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

    override suspend fun login(body: UserLoginRequest): Flow<Resource<Unit>> =
        object :
            NetworkBoundRequest<TokenResponse?>() {
            override suspend fun createCall(): Flow<RemoteResponse<TokenResponse?>> =
                remoteDataSource.login(body)

            override suspend fun saveCallResult(data: TokenResponse?) {
                if (data != null) {
                    datastore.saveBearerToken(data.token)
                    datastore.saveNIM(body.nim)
                }
            }
        }.asFlow()

    override suspend fun register(body: UserRegisterRequest): Flow<Resource<Unit>> =
        object :
            NetworkBoundRequest<TokenResponse?>() {
            override suspend fun createCall(): Flow<RemoteResponse<TokenResponse?>> =
                remoteDataSource.register(body)

            override suspend fun saveCallResult(data: TokenResponse?) {
                if (data != null) {
                    datastore.saveBearerToken(data.token)
                    datastore.saveNIM(body.nim)
                }
            }
        }.asFlow()

    override suspend fun getUserDetail(): Flow<Resource<User>> =
        object :
            NetworkOnlyResource<User, UserResponse?>() {
            override suspend fun createCall(): Flow<RemoteResponse<UserResponse?>> {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                val nim = datastore.readNIM().firstOrNull().orEmpty()
                return remoteDataSource.getUserDetail(token, nim)
            }

            override fun mapTransform(data: UserResponse?): User =
                data!!.toUser()
        }.asFlow()

    override suspend fun changePassword(body: UserPasswordRequest): Flow<Resource<Unit>> =
        object : NetworkOnlyResource<Unit, String?>() {
            override suspend fun createCall(): Flow<RemoteResponse<String?>> {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                val nim = datastore.readNIM().firstOrNull().orEmpty()
                return remoteDataSource.changePassword(token, nim, body)
            }

            override fun mapTransform(data: String?) {
                return
            }
        }.asFlow()

    override suspend fun changeWhatsapp(newWhatsapp: UserWhatsappRequest): Flow<Resource<Unit>> =
        object : NetworkOnlyResource<Unit, String?>() {
            override suspend fun createCall(): Flow<RemoteResponse<String?>> {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                val nim = datastore.readNIM().first()
                return remoteDataSource.changeWhatsapp(token, nim, newWhatsapp)
            }

            override fun mapTransform(data: String?) {
                return
            }
        }.asFlow()

    override suspend fun logout(): Flow<Resource<Unit>> = object : NetworkBoundRequest<String?>() {
        override suspend fun createCall(): Flow<RemoteResponse<String?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            return remoteDataSource.logout(token)
        }

        override suspend fun saveCallResult(data: String?) {
            datastore.apply {
                deleteNIM()
                deleteToken()
            }
        }
    }.asFlow()
}