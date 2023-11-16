package com.college.tangkis.data.repository.contact

import com.college.tangkis.core.base.NetworkOnlyResource
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.RemoteResponse
import com.college.tangkis.data.source.remote.api.model.request.contact.ContactRequest
import com.college.tangkis.data.source.remote.api.model.response.contact.EmergencyContactResponse
import com.college.tangkis.domain.model.contact.EmergencyContact
import com.college.tangkis.util.toEmergencyContact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val remoteDataSource: RemoteDataSource
) : ContactRepository {
    override suspend fun addContact(body: ContactRequest): Flow<Resource<Unit>> = object : NetworkOnlyResource<Unit, String?>() {
        override suspend fun createCall(): Flow<RemoteResponse<String?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.postContact(token, nim, body)
        }
        override fun mapTransform(data: String?) {
            return
        }
    }.asFlow()

    override suspend fun getContacts(): Flow<Resource<List<EmergencyContact>>> = object : NetworkOnlyResource<List<EmergencyContact>, List<EmergencyContactResponse>?>() {
        override suspend fun createCall(): Flow<RemoteResponse<List<EmergencyContactResponse>?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.getContact(token, nim)
        }
        override fun mapTransform(data: List<EmergencyContactResponse>?): List<EmergencyContact> = data!!.map { it.toEmergencyContact() }
    }.asFlow()


    override suspend fun deleteContact(contactId: String): Flow<Resource<Unit>> = object : NetworkOnlyResource<Unit, String?>() {
        override suspend fun createCall(): Flow<RemoteResponse<String?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.deleteContact(token, nim, contactId)
        }
        override fun mapTransform(data: String?) {
            return
        }
    }.asFlow()
}