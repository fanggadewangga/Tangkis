package com.college.tangkis.data.source.remote.api.service

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
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // User
    @POST("/register")
    suspend fun register(
        @Body body: UserRegisterRequest,
    ): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse>

    @POST("/login")
    suspend fun login(
        @Body body: UserLoginRequest,
    ): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse>

    @POST("/logout")
    suspend fun logout(
        @Header("Authorization") token: String,
    ): NetworkResponse<BaseResponse<String>, ErrorResponse>

    @PUT("/user/{nim}/whatsapp")
    suspend fun changeWhatsapp(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: UserWhatsappRequest,
    ):  NetworkResponse<BaseResponse<String>, ErrorResponse>

    @PUT("/user/{nim}/password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: UserPasswordRequest,
    ): NetworkResponse<BaseResponse<String>, ErrorResponse>

    @GET("/user/{nim}")
    suspend fun getUserDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): NetworkResponse<BaseResponse<UserResponse>, ErrorResponse>

    // Contact
    @POST("/user/{nim}/contact")
    suspend fun postContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: ContactRequest,
    ): NetworkResponse<BaseResponse<String>, ErrorResponse>

    @GET("/user/{nim}/contact")
    suspend fun getContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): NetworkResponse<BaseResponse<List<EmergencyContactResponse>>, ErrorResponse>

    @DELETE("/user/{nim}/contact/{contactId}")
    suspend fun deleteContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("contactId") contactId: String,
    ): NetworkResponse<BaseResponse<String>, ErrorResponse>

    // Report
    @POST("/user/{nim}/report")
    suspend fun postReport(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: ReportRequest,
    ): NetworkResponse<BaseResponse<AddReportResponse>, ErrorResponse>

    @GET("/user/{nim}/report")
    suspend fun getReports(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): NetworkResponse<BaseResponse<List<ReportListResponse>>, ErrorResponse>

    @GET("/user/{nim}/report/{reportId}")
    suspend fun getReportDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("reportId") reportId: String,
    ): NetworkResponse<BaseResponse<ReportDetailResponse>, ErrorResponse>

    // Consultation
    @POST("/user/{nim}/consultation")
    suspend fun postConsultation(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: ConsultationRequest,
    ): NetworkResponse<BaseResponse<AddConsultationResponse>, ErrorResponse>

    @GET("/user/{nim}/consultation")
    suspend fun getConsultations(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): NetworkResponse<BaseResponse<List<ConsultationListResponse>>, ErrorResponse>

    @GET("/user/{nim}/consultation/{consultationId}")
    suspend fun getConsultationDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("consultationId") consultationId: String,
    ): NetworkResponse<BaseResponse<ConsultationDetailResponse>, ErrorResponse>

    // Article
    @GET("/article")
    suspend fun getArticles(
        @Header("Authorization") token: String,
    ): NetworkResponse<BaseResponse<List<ArticleListResponse>>, ErrorResponse>

    @GET("/article")
    suspend fun searchArticle(
        @Header("Authorization") token: String,
        @Query("q") query: String,
    ): NetworkResponse<BaseResponse<List<ArticleListResponse>>, ErrorResponse>

    @GET("/article/{articleId}")
    suspend fun getArticleDetail(
        @Header("Authorization") token: String,
        @Path("articleId") articleId: String,
    ): NetworkResponse<BaseResponse<ArticleDetailResponse>, ErrorResponse>

    // Activity
    @GET("/user/{nim}/activity/progress")
    suspend fun getInProgressActivity(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): NetworkResponse<BaseResponse<List<ActivityResponse>>, ErrorResponse>

    @GET("/user/{nim}/activity/history")
    suspend fun getHistoryActivity(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): NetworkResponse<BaseResponse<List<ActivityResponse>>, ErrorResponse>
}