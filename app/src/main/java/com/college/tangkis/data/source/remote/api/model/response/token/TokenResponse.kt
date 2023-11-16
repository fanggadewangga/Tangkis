package com.college.tangkis.data.source.remote.api.model.response.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String
)