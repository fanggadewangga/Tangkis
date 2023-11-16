package com.college.tangkis.data.source.remote.api.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val nim: String,
    val password: String
)