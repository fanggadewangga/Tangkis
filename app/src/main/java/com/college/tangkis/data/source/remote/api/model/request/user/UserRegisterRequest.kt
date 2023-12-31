package com.college.tangkis.data.source.remote.api.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val name: String,
    val nim: String,
    val whatsapp: String,
    val password: String
)