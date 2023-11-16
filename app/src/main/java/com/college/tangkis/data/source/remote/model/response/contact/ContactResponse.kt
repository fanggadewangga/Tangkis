package com.college.tangkis.data.source.remote.model.response.contact

import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
    val contactId: String,
    val name: String,
    val number: String,
)
