package com.college.tangkis.feature.article.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.tangkis.data.Resource
import com.college.tangkis.data.repository.article.ArticleRepository
import com.college.tangkis.domain.model.article.ArticleList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val articleRepository: ArticleRepository): ViewModel() {
    val searchQuery = mutableStateOf("")

    private val _articleState = MutableStateFlow<Resource<List<ArticleList>>>(Resource.Loading())
    val articleState = _articleState.asStateFlow()

    private fun getArticle() {
        viewModelScope.launch {
            articleRepository.getArticles().collect {
                _articleState.value = it
            }
        }
    }

    fun searchArticle() {
        viewModelScope.launch {
            articleRepository.getArticles(searchQuery.value).collect {
                _articleState.value = it
            }
        }
    }

    init {
        getArticle()
    }
}