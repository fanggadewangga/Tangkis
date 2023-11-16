package com.college.tangkis.domain.model.report

data class ReportDetail(
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
