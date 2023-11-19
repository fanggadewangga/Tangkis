package com.college.tangkis.core.base

import com.college.tangkis.data.source.remote.RemoteResponse
import com.college.tangkis.data.source.remote.api.model.response.BaseResponse
import com.college.tangkis.data.source.remote.api.model.response.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRemoteResponse<RequestType> {
    private val value = flow {
        when (val response = call()) {
            is NetworkResponse.Success -> {
                val data = response.body.data
                emit(RemoteResponse.Success(data))
            }

            is NetworkResponse.Error -> emit(
                RemoteResponse.Error(
                    response.body?.message ?: response.error?.message.toString()
                )
            )
        }
    }.flowOn(Dispatchers.IO)

    abstract suspend fun call(): NetworkResponse<BaseResponse<RequestType>, ErrorResponse>
    fun asFlow() = value
}