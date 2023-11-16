package com.college.tangkis.feature.article.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.article.ArticleRepository
import com.college.tangkis.domain.model.article.ArticleDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articleState = MutableStateFlow<Resource<ArticleDetail>>(Resource.Loading())
    val articleState = _articleState.asStateFlow()

    fun getArticleDetail(articleId: String) {
        viewModelScope.launch {
            articleRepository.getArticleDetail(articleId).collect {
                _articleState.value = it
            }
        }
    }
}