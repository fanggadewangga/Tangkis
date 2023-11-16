package com.college.tangkis.data.source.remote

import android.util.Log
import com.college.tangkis.data.source.remote.model.response.BaseResponse
import com.college.tangkis.data.source.remote.model.response.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRemoteResponse<RequestType> {
    private val value = flow {
        when(val response = call()) {
            is NetworkResponse.Success -> {
                val data = response.body.data
                Log.d("Base Remote Response Success : ", data.toString())
                emit(RemoteResponse.Success(data))
            }
            is NetworkResponse.Error -> {
                Log.d("Base Remote Response Error : ",response.body?.message ?: response.error?.message.toString())
                emit(RemoteResponse.Error(response.body?.message ?: response.error?.message.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)

    abstract suspend fun call(): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<RequestType>, com.college.tangkis.data.source.remote.model.response.ErrorResponse>
    fun asFlow() = value
}