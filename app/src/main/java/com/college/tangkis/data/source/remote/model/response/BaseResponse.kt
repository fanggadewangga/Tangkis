package com.college.tangkis.data.source.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    var error: Boolean = false,
    var status: String = "",
    val message: String = "",
    val count: Int? = 0,
    val data: T?
)