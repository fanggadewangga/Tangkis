package com.college.tangkis.util

import com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse
import com.college.tangkis.data.source.remote.model.response.user.UserResponse
import com.college.tangkis.domain.model.activity.Activity
import com.college.tangkis.domain.model.user.User

fun UserResponse.toUser() = User(
    this.nim,
    this.name,
    this.whatsapp,
    this.studyProgram,
    this.password,
    this.salt
)

fun ActivityResponse.toActivity() = Activity(
    this.activityId,
    this.title,
    this.updateDate,
    this.progress
)