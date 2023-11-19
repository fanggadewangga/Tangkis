package com.college.tangkis.data.repository.article

import com.college.tangkis.core.base.NetworkOnlyResource
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.RemoteDataSource
import com.college.tangkis.data.source.remote.RemoteResponse
import com.college.tangkis.data.source.remote.api.model.response.article.ArticleDetailResponse
import com.college.tangkis.data.source.remote.api.model.response.article.ArticleListResponse
import com.college.tangkis.domain.model.article.ArticleDetail
import com.college.tangkis.domain.model.article.ArticleList
import com.college.tangkis.util.toArticleDetail
import com.college.tangkis.util.toArticleList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val remoteDataSource: RemoteDataSource,
) : ArticleRepository {
    override suspend fun getArticles(query: String): Flow<Resource<List<ArticleList>>> =
        object : NetworkOnlyResource<List<ArticleList>, List<ArticleListResponse>?>() {
            override suspend fun createCall(): Flow<RemoteResponse<List<ArticleListResponse>?>> {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                return if (query.isEmpty())
                    remoteDataSource.getArticles(token)
                else
                    remoteDataSource.searchArticle(token, query)
            }

            override fun mapTransform(data: List<ArticleListResponse>?): List<ArticleList> =
                data!!.map { it.toArticleList() }
        }.asFlow()

    override suspend fun getArticleDetail(articleId: String): Flow<Resource<ArticleDetail>> =
        object : NetworkOnlyResource<ArticleDetail, ArticleDetailResponse?>() {
            override suspend fun createCall(): Flow<RemoteResponse<ArticleDetailResponse?>> {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                return remoteDataSource.getArticleDetail(token, articleId)
            }

            override fun mapTransform(data: ArticleDetailResponse?): ArticleDetail =
                data!!.toArticleDetail()
        }.asFlow()
}