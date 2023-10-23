package com.college.tangkis.feature.article.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ArticleDetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<ArticleDetailViewModel>()
}