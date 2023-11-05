package com.college.tangkis_rpl.model

import kotlinx.serialization.Serializable

@Serializable
data class EmergencyContact(
    val contactId : String = "",
    val name: String = "",
    val number: String = "",
)