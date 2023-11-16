package com.college.tangkis.data.source.remote

import com.college.tangkis.data.source.remote.model.request.user.UserLoginRequest
import com.college.tangkis.data.source.remote.model.request.user.UserPasswordRequest
import com.college.tangkis.data.source.remote.model.request.user.UserRegisterRequest
import com.college.tangkis.data.source.remote.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.source.remote.model.response.BaseResponse
import com.college.tangkis.data.source.remote.model.response.ErrorResponse
import com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse
import com.college.tangkis.data.source.remote.model.response.token.TokenResponse
import com.college.tangkis.data.source.remote.model.response.user.UserResponse
import com.haroldadmin.cnradapter.NetworkResponse

class RemoteDataSource(
    private val apiService: ApiService,
) {
    // User
    suspend fun login(
        body: UserLoginRequest,
    ) = object : BaseRemoteResponse<TokenResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse> = apiService.login(body)
    }.asFlow()

    suspend fun register(
        body: UserRegisterRequest
    ) = object : BaseRemoteResponse<TokenResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse> = apiService.register(body)
    }.asFlow()

    suspend fun logout(
        token: String
    ) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.logout(token)
    }.asFlow()

    suspend fun changeWhatsapp(token: String, nim: String, body: UserWhatsappRequest) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.changeWhatsapp(token, nim, body)
    }.asFlow()

    suspend fun changePassword(token: String, nim: String, body: UserPasswordRequest) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.changePassword(token, nim, body)
    }.asFlow()

    suspend fun getUserDetail(token: String, nim: String) = object : BaseRemoteResponse<UserResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<UserResponse>, ErrorResponse> = apiService.getUserDetail(token, nim)
    }.asFlow()

    // Activity
    suspend fun getInProgressActivity(token: String, nim: String) = object : BaseRemoteResponse<List<ActivityResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ActivityResponse>>, ErrorResponse> = apiService.getInProgressActivity(token, nim)
    }.asFlow()

    suspend fun getHistoryActivity(token: String, nim: String) = object : BaseRemoteResponse<List<ActivityResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ActivityResponse>>, ErrorResponse> = apiService.getHistoryActivity(token, nim)
    }.asFlow()
}