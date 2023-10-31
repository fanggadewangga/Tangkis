package com.college.tangkis.data.repository.article

import com.college.model.response.article.ArticleResponse
import com.college.tangkis.data.Resource
import com.college.tangkis.data.model.response.article.ArticleListResponse
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(query: String? = ""): Flow<Resource<List<ArticleListResponse>>>
    suspend fun getArticleDetail(articleId: String): Flow<Resource<ArticleResponse>>
}