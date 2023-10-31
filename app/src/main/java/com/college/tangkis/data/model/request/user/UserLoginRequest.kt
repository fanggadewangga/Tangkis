package com.college.tangkis.data.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val nim: String,
    val password: String
)