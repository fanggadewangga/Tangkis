package com.college.tangkis.data.source.remote

import com.college.tangkis.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class NetworkBoundRequest<RequestType> {
    private val result: Flow<Resource<Unit>> = flow {
        preRequest()
        emit(Resource.Loading())
        when (val remoteResponse = createCall().first()) {
            is RemoteResponse.Success<RequestType> -> {
                saveCallResult(remoteResponse.data)
                emit(Resource.Success(Unit))
            }
            is RemoteResponse.Error -> {
                emit(
                    Resource.Error(
                        remoteResponse.errorMessage
                    )
                )
            }
            is RemoteResponse.Empty -> emit(Resource.Empty())
        }
    }.flowOn(Dispatchers.IO)

    protected open suspend fun preRequest(){}

    protected abstract suspend fun createCall(): Flow<RemoteResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow() = result
}