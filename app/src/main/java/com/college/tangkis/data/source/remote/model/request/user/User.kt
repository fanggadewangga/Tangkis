package com.college.tangkis.data.source.remote.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val nim: String,
    val name: String,
    val whatsapp: String,
    val studyProgram: String? = "",
    val password: String,
    val salt: String
)