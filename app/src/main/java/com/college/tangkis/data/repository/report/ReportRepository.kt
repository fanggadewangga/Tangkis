package com.college.tangkis.data.repository.report

import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.request.report.ReportRequest
import com.college.tangkis.data.model.response.report.ReportDetailResponse
import com.college.tangkis.data.model.response.report.ReportResponse
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun addReport(body: ReportRequest): Flow<Resource<String>>
    suspend fun getReports(): Flow<Resource<List<ReportResponse>>>
    suspend fun getReportDetail(reportId: String): Flow<Resource<ReportDetailResponse>>
}