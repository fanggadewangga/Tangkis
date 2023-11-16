package com.college.tangkis.feature.article.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.model.response.article.ArticleResponse
import com.college.tangkis.data.Resource
import com.college.tangkis.data.source.remote.model.response.article.ArticleListResponse
import com.college.tangkis.data.repository.article.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articleState = MutableStateFlow<Resource<ArticleResponse>>(Resource.Loading())
    val articleState = _articleState.asStateFlow()

    fun getArticleDetail(articleId: String) {
        viewModelScope.launch {
            articleRepository.getArticleDetail(articleId).collect {
                _articleState.value = it
            }
        }
    }
}