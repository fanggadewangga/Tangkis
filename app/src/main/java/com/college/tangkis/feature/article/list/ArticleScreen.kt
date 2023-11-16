package com.college.tangkis.feature.article.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppSearchField
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ArticleItem
import com.college.tangkis.feature.main.components.ArticleItemShimmer
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_primaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(navController: NavController) {

    val viewModel = hiltViewModel<ArticleViewModel>()
    val articleState = viewModel.articleState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Artikel Informasi",
                        color = Color.White,
                        textStyle = Typography.titleLarge()
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = md_theme_light_primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        AsyncImage(
                            model = R.drawable.ic_back,
                            contentDescription = "Back Icon",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { it ->
        val topPadding = it.calculateTopPadding()

        Column(modifier = Modifier.padding(top = topPadding + 16.dp)) {
            AppSearchField(
                valueState = viewModel.searchQuery.value,
                borderColor = md_theme_light_primaryContainer,
                placeholder = "Temukan Artikel Informasi",
                onValueChange = {
                    viewModel.apply {
                        searchQuery.value = it
                        searchArticle()
                    }
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            when (articleState.value) {
                is Resource.Loading -> {
                    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                        items(7) {
                            ArticleItemShimmer()
                        }
                    }
                }

                is Resource.Success -> {
                    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                        if (articleState.value is Resource.Success && !articleState.value.data.isNullOrEmpty())
                            items(articleState.value.data!!) {
                                ArticleItem(
                                    article = it
                                ) { id ->
                                    navController.navigate(
                                        Screen.ArticleDetail.route.replace(
                                            oldValue = "{articleId}",
                                            newValue = id
                                        )
                                    )
                                }
                            }
                    }
                }

                is Resource.Error -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        ErrorLayout(
                            image = R.drawable.iv_error,
                            message = "Daftar artikel informasi gagal ditampilkan"
                        )
                    }
                }

                else -> {}
            }
        }
    }
}