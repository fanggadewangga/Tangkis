package com.college.tangkis.data.source.remote

import com.college.tangkis.data.model.request.user.UserLoginRequest
import com.college.tangkis.data.model.response.BaseResponse
import com.college.tangkis.data.model.response.ErrorResponse
import com.college.tangkis.data.model.response.token.TokenResponse
import com.haroldadmin.cnradapter.NetworkResponse

class RemoteDataSource(
    private val apiService: ApiService,
) {
    suspend fun login(
        body: UserLoginRequest,
    ) = object : BaseRemoteResponse<TokenResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse> = apiService.login(body)
    }.asFlow()
}