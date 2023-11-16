package com.college.tangkis.data.repository.report

import com.college.tangkis.data.Resource
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun addReport(body: com.college.tangkis.data.source.remote.model.request.report.ReportRequest): Flow<Resource<com.college.tangkis.data.source.remote.model.response.report.AddReportResponse?>>
    suspend fun getReports(): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.report.ReportListResponse>>>
    suspend fun getReportDetail(reportId: String): Flow<Resource<com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse>>
}