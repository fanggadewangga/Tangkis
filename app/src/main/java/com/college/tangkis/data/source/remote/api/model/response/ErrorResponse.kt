package com.college.tangkis.data.source.remote.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    var error: Boolean = true,
    var status: String = "",
    val message: String = "",
)
