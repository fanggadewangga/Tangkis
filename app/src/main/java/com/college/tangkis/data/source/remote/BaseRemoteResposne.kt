package com.college.tangkis.data.source.remote

import android.util.Log
import com.college.tangkis.data.model.response.BaseResponse
import com.college.tangkis.data.model.response.ErrorResponse
import com.college.tangkis.data.model.response.token.TokenResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRemoteResponse<RequestType> {
    private val value = flow {
        when(val response = call()) {
            is NetworkResponse.Success -> {
                val data = response.body.data
                Log.d("Base Remote Response", data.toString())
                emit(RemoteResponse.Success(data))
            }
            is NetworkResponse.Error -> {
                emit(RemoteResponse.Error(response.body!!.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    abstract suspend fun call(): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse>
    fun asFlow() = value
}