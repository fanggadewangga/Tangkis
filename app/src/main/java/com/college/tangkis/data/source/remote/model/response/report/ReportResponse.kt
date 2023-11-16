package com.college.tangkis.data.source.remote.model.response.report

import kotlinx.serialization.Serializable

@Serializable
data class ReportResponse(
    val reportId: String,
    val title: String,
    val progress: String,
    val updatedAt: String,
)