package com.college.tangkis.data.source.remote.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserWhatsappRequest(
    val whatsapp: String
)
