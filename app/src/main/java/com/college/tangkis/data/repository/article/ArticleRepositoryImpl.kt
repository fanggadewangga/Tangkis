package com.college.tangkis.data.repository.article

import android.util.Log
import com.college.tangkis.data.source.remote.model.response.article.ArticleResponse
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.local.TangkisDatastore
import com.college.tangkis.data.source.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val datastore: TangkisDatastore,
    private val apiService: ApiService,
) : ArticleRepository {
    override suspend fun getArticles(query: String?): Flow<Resource<List<com.college.tangkis.data.source.remote.model.response.article.ArticleListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val token = "Bearer ${datastore.readBearerToken().first()}"
                val result = if (query.isNullOrEmpty()) apiService.getArticles(token) else apiService.searchArticle(token, query)
                if (result.error) {
                    Log.d("Get/Search Article", result.message)
                    emit(Resource.Error(result.message))
                } else {
                    Log.d("Get/Search Article", result.message)
                    emit(Resource.Success(result.data!!))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getArticleDetail(articleId: String): Flow<Resource<ArticleResponse>> = flow {
        emit(Resource.Loading())
        try {
            val token = "Bearer ${datastore.readBearerToken().first()}"
            val result = apiService.getArticleDetail(token, articleId)
            if (result.error) {
                Log.d("Get Article Detail", result.message)
                emit(Resource.Error(result.message))
            } else {
                Log.d("Get Article Detail", result.message)
                emit(Resource.Success(result.data!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}