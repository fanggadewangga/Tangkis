package com.college.tangkis.data.source.remote

import com.college.tangkis.core.base.BaseRemoteResponse
import com.college.tangkis.data.source.remote.api.model.request.consultation.ConsultationRequest
import com.college.tangkis.data.source.remote.api.model.request.contact.ContactRequest
import com.college.tangkis.data.source.remote.api.model.request.report.ReportRequest
import com.college.tangkis.data.source.remote.api.model.request.user.UserLoginRequest
import com.college.tangkis.data.source.remote.api.model.request.user.UserPasswordRequest
import com.college.tangkis.data.source.remote.api.model.request.user.UserRegisterRequest
import com.college.tangkis.data.source.remote.api.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.source.remote.api.model.response.BaseResponse
import com.college.tangkis.data.source.remote.api.model.response.ErrorResponse
import com.college.tangkis.data.source.remote.api.model.response.activity.ActivityResponse
import com.college.tangkis.data.source.remote.api.model.response.article.ArticleDetailResponse
import com.college.tangkis.data.source.remote.api.model.response.article.ArticleListResponse
import com.college.tangkis.data.source.remote.api.model.response.consultation.AddConsultationResponse
import com.college.tangkis.data.source.remote.api.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.source.remote.api.model.response.consultation.ConsultationListResponse
import com.college.tangkis.data.source.remote.api.model.response.contact.EmergencyContactResponse
import com.college.tangkis.data.source.remote.api.model.response.report.AddReportResponse
import com.college.tangkis.data.source.remote.api.model.response.report.ReportDetailResponse
import com.college.tangkis.data.source.remote.api.model.response.report.ReportListResponse
import com.college.tangkis.data.source.remote.api.model.response.token.TokenResponse
import com.college.tangkis.data.source.remote.api.model.response.user.UserResponse
import com.college.tangkis.data.source.remote.api.service.ApiService
import com.haroldadmin.cnradapter.NetworkResponse

class RemoteDataSource(
    private val apiService: ApiService,
) {
    // User
    suspend fun login(
        body: UserLoginRequest,
    ) = object : BaseRemoteResponse<TokenResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse> = apiService.login(body)
    }.asFlow()

    suspend fun register(
        body: UserRegisterRequest
    ) = object : BaseRemoteResponse<TokenResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse> = apiService.register(body)
    }.asFlow()

    suspend fun logout(
        token: String
    ) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.logout(token)
    }.asFlow()

    suspend fun changeWhatsapp(token: String, nim: String, body: UserWhatsappRequest) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.changeWhatsapp(token, nim, body)
    }.asFlow()

    suspend fun changePassword(token: String, nim: String, body: UserPasswordRequest) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.changePassword(token, nim, body)
    }.asFlow()

    suspend fun getUserDetail(token: String, nim: String) = object : BaseRemoteResponse<UserResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<UserResponse>, ErrorResponse> = apiService.getUserDetail(token, nim)
    }.asFlow()

    // Contact
    suspend fun postContact(token: String, nim: String, body: ContactRequest) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.postContact(token, nim, body)
    }.asFlow()

    suspend fun getContact(token: String, nim: String) = object : BaseRemoteResponse<List<EmergencyContactResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<EmergencyContactResponse>>, ErrorResponse> = apiService.getContact(token, nim)
    }.asFlow()

    suspend fun deleteContact(token: String, nim: String, contactId: String) = object : BaseRemoteResponse<String>() {
        override suspend fun call(): NetworkResponse<BaseResponse<String>, ErrorResponse> = apiService.deleteContact(token, nim, contactId)
    }.asFlow()

    // Report
    suspend fun postReport(token: String, nim: String, body: ReportRequest) = object : BaseRemoteResponse<AddReportResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<AddReportResponse>, ErrorResponse> = apiService.postReport(token, nim, body)
    }.asFlow()

    suspend fun getReports(token: String, nim: String) = object : BaseRemoteResponse<List<ReportListResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ReportListResponse>>, ErrorResponse> = apiService.getReports(token, nim)
    }.asFlow()

    suspend fun getReportDetail(token: String, nim: String, reportId: String) = object : BaseRemoteResponse<ReportDetailResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<ReportDetailResponse>, ErrorResponse> = apiService.getReportDetail(token, nim, reportId)
    }.asFlow()

    // Consultation
    suspend fun postConsultation(token: String, nim: String, body: ConsultationRequest) = object : BaseRemoteResponse<AddConsultationResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<AddConsultationResponse>, ErrorResponse> = apiService.postConsultation(token, nim, body)
    }.asFlow()

    suspend fun getConsultation(token: String, nim: String) = object : BaseRemoteResponse<List<ConsultationListResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ConsultationListResponse>>, ErrorResponse> = apiService.getConsultations(token, nim)
    }.asFlow()

    suspend fun getConsultationDetail(token: String, nim: String, consultationId: String) = object : BaseRemoteResponse<ConsultationDetailResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<ConsultationDetailResponse>, ErrorResponse> = apiService.getConsultationDetail(token, nim, consultationId)
    }.asFlow()

    // Article
    suspend fun getArticles(token: String) = object : BaseRemoteResponse<List<ArticleListResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ArticleListResponse>>, ErrorResponse> = apiService.getArticles(token)
    }.asFlow()

    suspend fun searchArticle(token: String, query: String) = object : BaseRemoteResponse<List<ArticleListResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ArticleListResponse>>, ErrorResponse> = apiService.searchArticle(token, query)
    }.asFlow()

    suspend fun getArticleDetail(token: String, articleId: String) = object : BaseRemoteResponse<ArticleDetailResponse>() {
        override suspend fun call(): NetworkResponse<BaseResponse<ArticleDetailResponse>, ErrorResponse> = apiService.getArticleDetail(token, articleId)
    }.asFlow()

    // Activity
    suspend fun getInProgressActivity(token: String, nim: String) = object : BaseRemoteResponse<List<ActivityResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ActivityResponse>>, ErrorResponse> = apiService.getInProgressActivity(token, nim)
    }.asFlow()

    suspend fun getHistoryActivity(token: String, nim: String) = object : BaseRemoteResponse<List<ActivityResponse>>() {
        override suspend fun call(): NetworkResponse<BaseResponse<List<ActivityResponse>>, ErrorResponse> = apiService.getHistoryActivity(token, nim)
    }.asFlow()
}