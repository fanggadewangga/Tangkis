package com.college.tangkis.data.model.response.consultation

import kotlinx.serialization.Serializable

@Serializable
data class AddConsultationResponse(
    val consultationId: String
)
