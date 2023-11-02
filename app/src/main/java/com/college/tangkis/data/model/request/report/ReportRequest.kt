package com.college.tangkis.data.model.request.report

import com.college.tangkis.data.model.request.consultation.ConsultationRequest
import kotlinx.serialization.Serializable

@Serializable
data class ReportRequest(
    val story: String,
    val isNeedConsultation: Boolean,
    val consultation: ConsultationRequest? = null
)
