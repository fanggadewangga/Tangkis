package com.college.tangkis.data.source.remote.api.model.request.consultation

import kotlinx.serialization.Serializable

@Serializable
data class ConsultationRequest(
    val story: String,
    val counselorChoice: Int = 0,
    val consultationType: Int,
    val date: String,
    val time: String
)
