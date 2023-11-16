package com.college.tangkis.data.source.remote

import com.college.tangkis.data.source.remote.model.request.user.UserLoginRequest
import com.college.tangkis.data.source.remote.model.request.user.UserPasswordRequest
import com.college.tangkis.data.source.remote.model.request.user.UserRegisterRequest
import com.college.tangkis.data.source.remote.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.source.remote.model.response.BaseResponse
import com.college.tangkis.data.source.remote.model.response.ErrorResponse
import com.college.tangkis.data.source.remote.model.response.token.TokenResponse
import com.college.tangkis.data.source.remote.model.response.user.UserResponse
import com.haroldadmin.cnradapter.NetworkResponse

class RemoteDataSource(
    private val apiService: ApiService,
) {
    // User
    suspend fun login(
        body: com.college.tangkis.data.source.remote.model.request.user.UserLoginRequest,
    ) = object : BaseRemoteResponse<com.college.tangkis.data.source.remote.model.response.token.TokenResponse>() {
        override suspend fun call(): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.token.TokenResponse>, com.college.tangkis.data.source.remote.model.response.ErrorResponse> = apiService.login(body)
    }.asFlow()

    suspend fun register(
        body: com.college.tangkis.data.source.remote.model.request.user.UserRegisterRequest
    ) = object : BaseRemoteResponse<com.college.tangkis.data.source.remote.model.response.token.TokenResponse>() {
        override suspend fun call(): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.token.TokenResponse>, com.college.tangkis.data.source.remote.model.response.ErrorResponse> = apiService.register(body)
    }.asFlow()

    suspend fun logout(
        token: String
    ) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<String>, com.college.tangkis.data.source.remote.model.response.ErrorResponse> = apiService.logout(token)
    }.asFlow()

    suspend fun changeWhatsapp(token: String, nim: String, body: com.college.tangkis.data.source.remote.model.request.user.UserWhatsappRequest) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<String>, com.college.tangkis.data.source.remote.model.response.ErrorResponse> = apiService.changeWhatsapp(token, nim, body)
    }.asFlow()

    suspend fun changePassword(token: String, nim: String, body: com.college.tangkis.data.source.remote.model.request.user.UserPasswordRequest) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<String>, com.college.tangkis.data.source.remote.model.response.ErrorResponse> = apiService.changePassword(token, nim, body)
    }.asFlow()

    suspend fun getUserDetail(token: String, nim: String) = object : BaseRemoteResponse<com.college.tangkis.data.source.remote.model.response.user.UserResponse>() {
        override suspend fun call(): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.user.UserResponse>, com.college.tangkis.data.source.remote.model.response.ErrorResponse> = apiService.getUserDetail(token, nim)
    }.asFlow()
}