package com.college.tangkis_rpl.model

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import kotlinx.serialization.Serializable

data class ArtikelInformasi(
    val articleId: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val content: String = "",
    val postDate: String = "",
) {

}
