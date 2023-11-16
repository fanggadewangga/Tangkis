package com.college.tangkis.data.repository.report

import com.college.tangkis.data.Resource
import com.college.tangkis.domain.model.report.AddReport
import com.college.tangkis.domain.model.report.ReportDetail
import com.college.tangkis.domain.model.report.ReportList
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun addReport(body: com.college.tangkis.data.source.remote.model.request.report.ReportRequest): Flow<Resource<AddReport>>
    suspend fun getReports(): Flow<Resource<List<ReportList>>>
    suspend fun getReportDetail(reportId: String): Flow<Resource<ReportDetail>>
}