package com.college.tangkis.data.source.remote.api.model.response.consultation

import kotlinx.serialization.Serializable

@Serializable
data class ConsultationDetailResponse(
    val consultationId: String,
    val title: String,
    val uid: String,
    val story: String,
    val progress: String,
    val counselorChoice: String,
    val consultationType: String,
    val date: String,
    val time: String,
    val createdAt: String,
    val updatedAt: String,
)
