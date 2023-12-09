package com.college.tangkis.domain.model.contact

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class DeviceContact(
    val name: String,
    @PrimaryKey val number: String,
)
