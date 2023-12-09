package com.college.tangkis.data.repository.contact

import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.api.model.request.contact.ContactRequest
import com.college.tangkis.domain.model.contact.DeviceContact
import com.college.tangkis.domain.model.contact.EmergencyContact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun addContact(body: ContactRequest): Flow<Resource<Unit>>
    suspend fun getContacts(): Flow<Resource<List<EmergencyContact>>>
    suspend fun deleteContact(contactId: String): Flow<Resource<Unit>>
    suspend fun saveDeviceContactToRoom(deviceContacts: List<DeviceContact>)
    fun getSavedContacts(query: String = ""): Flow<Resource<List<DeviceContact>>>
}