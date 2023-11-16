package com.college.tangkis.data.repository.contact

import android.util.Log
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val apiService: ApiService,
) : ContactRepository {
    override suspend fun addContact(body: com.college.tangkis.data.source.remote.model.request.contact.ContactRequest): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.postContact(token, nim, body)
            if (result.error) {
                Log.d("Add Contact", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Add Contact", result.message)
                emit(Resource.Success(result.message))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getContacts(): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.contact.EmergencyContactResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.getContact(token, nim)
            if (result.error) {
                Log.d("Get Contact", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Get Contact", result.message)
                emit(Resource.Success(result.data!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun deleteContact(contactId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            val result = apiService.deleteContact(token, nim, contactId)
            if (result.error) {
                Log.d("Delete Contact", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Delete Contact", result.message)
                emit(Resource.Success(result.data!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}