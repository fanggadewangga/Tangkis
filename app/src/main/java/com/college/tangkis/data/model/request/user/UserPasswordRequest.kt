package com.college.tangkis.data.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserPasswordRequest(
    val password: String
)