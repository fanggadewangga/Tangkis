package com.college.tangkis.data.source.remote.model.response.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val articleId: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val date: String
)
