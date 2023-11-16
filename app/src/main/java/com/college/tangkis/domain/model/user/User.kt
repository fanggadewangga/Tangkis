package com.college.tangkis.domain.model.user

data class User(
    val nim: String,
    val name: String,
    val whatsapp: String,
    val studyProgram: String? = "",
    val password: String,
    val salt: String
)
