package com.college.tangkis.util

import com.college.tangkis.data.source.remote.model.response.user.UserResponse
import com.college.tangkis.domain.model.User

fun UserResponse.toUser() = User(
    this.nim,
    this.name,
    this.whatsapp,
    this.studyProgram,
    this.password,
    this.salt
)