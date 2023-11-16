package com.college.tangkis.data.source.remote.api.model.response.report

import kotlinx.serialization.Serializable

@Serializable
data class ReportListResponse(
    val reportId: String,
    val title: String,
    val progress: String,
    val updatedAt: String,
)
