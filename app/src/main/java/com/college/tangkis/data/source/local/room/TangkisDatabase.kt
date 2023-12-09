package com.college.tangkis.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.college.tangkis.domain.model.contact.DeviceContact

@Database(
    entities = [DeviceContact::class],
    version = 1
)
abstract class TangkisDatabase: RoomDatabase() {
    abstract val dao: TangkisDao
}