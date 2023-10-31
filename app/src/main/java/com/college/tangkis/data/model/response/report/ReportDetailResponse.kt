package com.college.tangkis.data.model.response.report

import kotlinx.serialization.Serializable
@Serializable
data class ReportDetailResponse(
    val reportId: String,
    val nim: String,
    val name: String,
    val whatsapp: String,
    val story: String,
    val isNeedConsultation: Boolean,
    val consultationId: String? = "",
    val progress: String,
    val date: String
)
