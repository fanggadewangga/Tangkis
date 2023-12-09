package com.college.tangkis.data.source.local.room

import androidx.room.*
import com.college.tangkis.domain.model.contact.DeviceContact

@Dao
interface TangkisDao {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    suspend fun getDeviceContacts(): List<DeviceContact>

    @Query("SELECT * FROM contact WHERE name LIKE '%' || :name || '%'")
    suspend fun searchContact(name: String): List<DeviceContact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(note: DeviceContact)
}