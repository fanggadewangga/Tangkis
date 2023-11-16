package com.college.tangkis.data.source.remote.api.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserPasswordRequest(
    val oldPassword: String,
    val newPassword: String
)
