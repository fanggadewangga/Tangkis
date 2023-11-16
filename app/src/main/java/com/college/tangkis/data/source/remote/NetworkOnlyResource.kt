package com.college.tangkis.data.source.remote

import android.util.Log
import com.college.tangkis.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class NetworkOnlyResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is RemoteResponse.Success -> {
                Log.d("Network only resource", apiResponse.data.toString())
                emit(Resource.Success(mapTransform(apiResponse.data)))
            }
            is RemoteResponse.Empty -> emit(Resource.Empty())
            is RemoteResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
        }
    }.flowOn(Dispatchers.IO)

    protected abstract suspend fun createCall(): Flow<RemoteResponse<RequestType>>

    protected abstract fun mapTransform(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result
}