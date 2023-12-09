package com.college.tangkis.data.source.local

import com.college.tangkis.data.source.local.datastore.TangkisDatastore
import com.college.tangkis.data.source.local.room.TangkisDao
import com.college.tangkis.domain.model.contact.DeviceContact

class LocalDataSource(
    private val datastore: TangkisDatastore,
    private val dao: TangkisDao
) {
    suspend fun insertContact(contact: DeviceContact) {
        dao.insertContact(contact)
    }

    suspend fun getContacts(query: String = ""): List<DeviceContact>{
        return if (query == "") dao.getDeviceContacts()
        else dao.searchContact(query)
    }
}