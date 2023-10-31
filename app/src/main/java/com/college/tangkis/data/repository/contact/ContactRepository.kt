package com.college.tangkis.data.repository.contact

import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.contact.ContactRequest
import com.college.tangkis.data.model.response.contact.ContactResponse
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun addContact(body: ContactRequest): Flow<Resource<String>>
    suspend fun getContacts(): Flow<Resource<List<ContactResponse>>>
    suspend fun deleteContact(contactId: String): Flow<Resource<String>>
}