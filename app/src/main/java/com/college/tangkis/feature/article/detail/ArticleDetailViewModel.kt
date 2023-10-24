package com.college.tangkis.feature.article.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ArticleDetailViewModel @Inject constructor() : ViewModel() {
    val isError = mutableStateOf(false)
}