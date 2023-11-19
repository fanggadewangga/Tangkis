package com.college.tangkis.data.repository.article

import com.college.tangkis.data.Resource
import com.college.tangkis.domain.model.article.ArticleDetail
import com.college.tangkis.domain.model.article.ArticleList
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(query: String = ""): Flow<Resource<List<ArticleList>>>
    suspend fun getArticleDetail(articleId: String): Flow<Resource<ArticleDetail>>
}