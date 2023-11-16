package com.college.tangkis.data.repository.report

import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.request.report.ReportRequest
import com.college.tangkis.data.source.remote.model.response.report.AddReportResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportResponse
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun addReport(body: com.college.tangkis.data.source.remote.model.request.report.ReportRequest): Flow<Resource<com.college.tangkis.data.source.remote.model.response.report.AddReportResponse?>>
    suspend fun getReports(): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.report.ReportResponse>>>
    suspend fun getReportDetail(reportId: String): Flow<Resource<com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse>>
}