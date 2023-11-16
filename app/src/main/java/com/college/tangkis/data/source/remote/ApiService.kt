package com.college.tangkis.data.source.remote

import com.college.model.response.article.ArticleResponse
import com.college.tangkis.data.source.remote.model.request.consultation.ConsultationRequest
import com.college.tangkis.data.source.remote.model.request.contact.ContactRequest
import com.college.tangkis.data.source.remote.model.request.report.ReportRequest
import com.college.tangkis.data.source.remote.model.request.user.UserLoginRequest
import com.college.tangkis.data.source.remote.model.request.user.UserPasswordRequest
import com.college.tangkis.data.source.remote.model.request.user.UserRegisterRequest
import com.college.tangkis.data.source.remote.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.source.remote.model.response.BaseResponse
import com.college.tangkis.data.source.remote.model.response.ErrorResponse
import com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse
import com.college.tangkis.data.source.remote.model.response.article.ArticleListResponse
import com.college.tangkis.data.source.remote.model.response.consultation.AddConsultationResponse
import com.college.tangkis.data.source.remote.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.source.remote.model.response.consultation.ConsultationListResponse
import com.college.tangkis.data.source.remote.model.response.contact.ContactResponse
import com.college.tangkis.data.source.remote.model.response.report.AddReportResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportResponse
import com.college.tangkis.data.source.remote.model.response.token.TokenResponse
import com.college.tangkis.data.source.remote.model.response.user.UserResponse
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
        @Body body: com.college.tangkis.data.source.remote.model.request.user.UserRegisterRequest,
    ): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.token.TokenResponse>, com.college.tangkis.data.source.remote.model.response.ErrorResponse>

    @POST("/login")
    suspend fun login(
        @Body body: com.college.tangkis.data.source.remote.model.request.user.UserLoginRequest,
    ): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.token.TokenResponse>, com.college.tangkis.data.source.remote.model.response.ErrorResponse>

    @POST("/logout")
    suspend fun logout(
        @Header("Authorization") token: String,
    ): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<String>, com.college.tangkis.data.source.remote.model.response.ErrorResponse>

    @PUT("/user/{nim}/whatsapp")
    suspend fun changeWhatsapp(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: com.college.tangkis.data.source.remote.model.request.user.UserWhatsappRequest,
    ):  NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<String>, com.college.tangkis.data.source.remote.model.response.ErrorResponse>

    @PUT("/user/{nim}/password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: com.college.tangkis.data.source.remote.model.request.user.UserPasswordRequest,
    ): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<String>, com.college.tangkis.data.source.remote.model.response.ErrorResponse>

    @GET("/user/{nim}")
    suspend fun getUserDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): NetworkResponse<com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.user.UserResponse>, com.college.tangkis.data.source.remote.model.response.ErrorResponse>

    // Contact
    @POST("/user/{nim}/contact")
    suspend fun postContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: com.college.tangkis.data.source.remote.model.request.contact.ContactRequest,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<String>

    @GET("/user/{nim}/contact")
    suspend fun getContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<List<com.college.tangkis.data.source.remote.model.response.contact.ContactResponse>>

    @DELETE("/user/{nim}/contact/{contactId}")
    suspend fun deleteContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("contactId") contactId: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<String>

    // Report
    @POST("/user/{nim}/report")
    suspend fun postReport(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: com.college.tangkis.data.source.remote.model.request.report.ReportRequest,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.report.AddReportResponse>

    @GET("/user/{nim}/report")
    suspend fun getReports(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<List<com.college.tangkis.data.source.remote.model.response.report.ReportResponse>>

    @GET("/user/{nim}/report/{reportId}")
    suspend fun getReportDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("reportId") reportId: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse>

    // Consultation
    @POST("/user/{nim}/consultation")
    suspend fun postConsultation(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: com.college.tangkis.data.source.remote.model.request.consultation.ConsultationRequest,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.consultation.AddConsultationResponse>

    @GET("/user/{nim}/consultation")
    suspend fun getConsultations(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<List<com.college.tangkis.data.source.remote.model.response.consultation.ConsultationListResponse>>

    @GET("/user/{nim}/consultation/{consultationId}")
    suspend fun getConsultationDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("consultationId") consultationId: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<com.college.tangkis.data.source.remote.model.response.consultation.ConsultationDetailResponse>

    // Article
    @GET("/article")
    suspend fun getArticles(
        @Header("Authorization") token: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<List<com.college.tangkis.data.source.remote.model.response.article.ArticleListResponse>>

    @GET("/article")
    suspend fun searchArticle(
        @Header("Authorization") token: String,
        @Query("q") query: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<List<com.college.tangkis.data.source.remote.model.response.article.ArticleListResponse>>

    @GET("/article/{articleId}")
    suspend fun getArticleDetail(
        @Header("Authorization") token: String,
        @Path("articleId") articleId: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<ArticleResponse>

    // Activity
    @GET("/user/{nim}/activity/progress")
    suspend fun getInProgressActivity(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<List<com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse>>

    @GET("/user/{nim}/activity/history")
    suspend fun getHistoryActivity(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): com.college.tangkis.data.source.remote.model.response.BaseResponse<List<com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse>>
}