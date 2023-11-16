package com.college.tangkis.data.source.remote.api.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserWhatsappRequest(
    val whatsapp: String
)
