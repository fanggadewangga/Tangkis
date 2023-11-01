package com.college.tangkis.data.model.response.consultation

import kotlinx.serialization.Serializable

@Serializable
data class ConsultationListResponse(
    val consultationId: String,
    val title: String,
    val updatedAt: String,
)
