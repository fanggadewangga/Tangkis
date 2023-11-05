package com.college.tangkis_rpl.model

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val articleId: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val content: String = "",
    val postDate: String = "",
) {
    suspend fun getArticle(client: HttpClient): List<Article> {
        var article: List<Article> = emptyList()
        try {
            val response =
                client.get<BaseResponse<List<Article>>>("https://tangkis-api.up.railway.app/article") {
                    headers {
                        append(
                            "Authorization",
                            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJUYW5na2lzLVVzZXIiLCJuYW5vX2lkIjoiMjE1MTUwMjAwMTExMDMzIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwLyIsImV4cCI6MTcyOTk0NzQ4NH0.hGrDN8HQjzdQtJuz57rzfZtliQtFcuizTE0BjBtfmng"
                        )
                    }
                }

            if (!response.error)
                article = response.data!!
            Log.d("RESPONSE SIZE", response.data!!.size.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return article
    }
}
