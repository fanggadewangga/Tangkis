package com.college.tangkis.data.source.remote

import com.college.model.response.article.ArticleResponse
import com.college.tangkis.data.model.request.consultation.ConsultationRequest
import com.college.tangkis.data.model.request.contact.ContactRequest
import com.college.tangkis.data.model.request.report.ReportRequest
import com.college.tangkis.data.model.request.user.UserLoginRequest
import com.college.tangkis.data.model.request.user.UserPasswordRequest
import com.college.tangkis.data.model.request.user.UserRegisterRequest
import com.college.tangkis.data.model.request.user.UserWhatsappRequest
import com.college.tangkis.data.model.response.BaseResponse
import com.college.tangkis.data.model.response.ErrorResponse
import com.college.tangkis.data.model.response.activity.ActivityResponse
import com.college.tangkis.data.model.response.article.ArticleListResponse
import com.college.tangkis.data.model.response.consultation.AddConsultationResponse
import com.college.tangkis.data.model.response.consultation.ConsultationDetailResponse
import com.college.tangkis.data.model.response.consultation.ConsultationListResponse
import com.college.tangkis.data.model.response.contact.ContactResponse
import com.college.tangkis.data.model.response.report.AddReportResponse
import com.college.tangkis.data.model.response.report.ReportDetailResponse
import com.college.tangkis.data.model.response.report.ReportResponse
import com.college.tangkis.data.model.response.token.TokenResponse
import com.college.tangkis.data.model.response.user.UserResponse
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
    ): BaseResponse<TokenResponse>

    @POST("/login")
    suspend fun login(
        @Body body: UserLoginRequest,
    ): NetworkResponse<BaseResponse<TokenResponse>, ErrorResponse>

    @POST("/logout")
    suspend fun logout(
        @Header("Authorization") token: String,
    ): BaseResponse<String>

    @PUT("/user/{nim}/whatsapp")
    suspend fun changeWhatsapp(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: UserWhatsappRequest,
    ): BaseResponse<String>

    @PUT("/user/{nim}/password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: UserPasswordRequest,
    ): BaseResponse<String>

    @GET("/user/{nim}")
    suspend fun getUserDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): BaseResponse<UserResponse>

    // Contact
    @POST("/user/{nim}/contact")
    suspend fun postContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: ContactRequest,
    ): BaseResponse<String>

    @GET("/user/{nim}/contact")
    suspend fun getContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): BaseResponse<List<ContactResponse>>

    @DELETE("/user/{nim}/contact/{contactId}")
    suspend fun deleteContact(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("contactId") contactId: String,
    ): BaseResponse<String>

    // Report
    @POST("/user/{nim}/report")
    suspend fun postReport(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: ReportRequest,
    ): BaseResponse<AddReportResponse>

    @GET("/user/{nim}/report")
    suspend fun getReports(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): BaseResponse<List<ReportResponse>>

    @GET("/user/{nim}/report/{reportId}")
    suspend fun getReportDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("reportId") reportId: String,
    ): BaseResponse<ReportDetailResponse>

    // Consultation
    @POST("/user/{nim}/consultation")
    suspend fun postConsultation(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Body body: ConsultationRequest,
    ): BaseResponse<AddConsultationResponse>

    @GET("/user/{nim}/consultation")
    suspend fun getConsultations(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): BaseResponse<List<ConsultationListResponse>>

    @GET("/user/{nim}/consultation/{consultationId}")
    suspend fun getConsultationDetail(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
        @Path("consultationId") consultationId: String,
    ): BaseResponse<ConsultationDetailResponse>

    // Article
    @GET("/article")
    suspend fun getArticles(
        @Header("Authorization") token: String,
    ): BaseResponse<List<ArticleListResponse>>

    @GET("/article")
    suspend fun searchArticle(
        @Header("Authorization") token: String,
        @Query("q") query: String,
    ): BaseResponse<List<ArticleListResponse>>

    @GET("/article/{articleId}")
    suspend fun getArticleDetail(
        @Header("Authorization") token: String,
        @Path("articleId") articleId: String,
    ): BaseResponse<ArticleResponse>

    // Activity
    @GET("/user/{nim}/activity/progress")
    suspend fun getInProgressActivity(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): BaseResponse<List<ActivityResponse>>

    @GET("/user/{nim}/activity/history")
    suspend fun getHistoryActivity(
        @Header("Authorization") token: String,
        @Path("nim") nim: String,
    ): BaseResponse<List<ActivityResponse>>
}