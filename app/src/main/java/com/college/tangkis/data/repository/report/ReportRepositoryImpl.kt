package com.college.tangkis.data.repository.report

import com.college.tangkis.core.base.NetworkOnlyResource
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.RemoteResponse
import com.college.tangkis.data.source.remote.api.model.request.report.ReportRequest
import com.college.tangkis.data.source.remote.api.model.response.report.AddReportResponse
import com.college.tangkis.data.source.remote.api.model.response.report.ReportDetailResponse
import com.college.tangkis.data.source.remote.api.model.response.report.ReportListResponse
import com.college.tangkis.domain.model.report.AddReport
import com.college.tangkis.domain.model.report.ReportDetail
import com.college.tangkis.domain.model.report.ReportList
import com.college.tangkis.util.toAddReport
import com.college.tangkis.util.toReportDetail
import com.college.tangkis.util.toReportList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val remoteDataSource: RemoteDataSource
) : ReportRepository {
    override suspend fun addReport(body: ReportRequest): Flow<Resource<AddReport>> = object : NetworkOnlyResource<AddReport, AddReportResponse?>() {
        override suspend fun createCall(): Flow<RemoteResponse<AddReportResponse?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.postReport(token, nim, body)
        }
        override fun mapTransform(data: AddReportResponse?): AddReport = data!!.toAddReport()
    }.asFlow()

    override suspend fun getReports(): Flow<Resource<List<ReportList>>> = object : NetworkOnlyResource<List<ReportList>, List<ReportListResponse>?>() {
        override suspend fun createCall(): Flow<RemoteResponse<List<ReportListResponse>?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.getReports(token, nim)
        }
        override fun mapTransform(data: List<ReportListResponse>?): List<ReportList> = data!!.map { it.toReportList() }
    }.asFlow()

    override suspend fun getReportDetail(reportId: String): Flow<Resource<ReportDetail>> = object : NetworkOnlyResource<ReportDetail, ReportDetailResponse?>() {
        override suspend fun createCall(): Flow<RemoteResponse<ReportDetailResponse?>> {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val nim = datastore.readNIM().first()
            return remoteDataSource.getReportDetail(token, nim, reportId)
        }
        override fun mapTransform(data: ReportDetailResponse?): ReportDetail = data!!.toReportDetail()
    }.asFlow()
}