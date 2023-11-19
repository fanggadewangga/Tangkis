package com.college.tangkis.feature.article.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.data.Resource
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(navController: NavController, articleId: String) {

    val viewModel = hiltViewModel<ArticleDetailViewModel>()
    val articleState = viewModel.articleState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getArticleDetail(articleId)
    }

    Scaffold(
        topBar = {
            if (articleState.value is Resource.Error)
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
    ) {
        val topPadding = it.calculateTopPadding()

        when (articleState.value) {
            is Resource.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(color = md_theme_light_primary)
                }
            }

            is Resource.Error -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    ErrorLayout(
                        image = R.drawable.iv_error,
                        message = "Artikel informasi gagal ditampilkan"
                    )
                }
            }

            is Resource.Success -> {

            val data = articleState.value.data!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = topPadding)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {

                    // Image
                    Box(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = data.imageUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = "Article image",
                            modifier = Modifier.fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back icon",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.TopStart)
                                    .clickable {
                                        navController.popBackStack()
                                    }
                            )
                        }
                    }

                    // Title
                    Spacer(modifier = Modifier.height(16.dp))
                    AppText(
                        text = data.title,
                        textStyle = Typography.titleLarge(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    // Date Time
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Image(
                            imageVector = Icons.Default.AccessTimeFilled,
                            contentDescription = "Time icon",
                            colorFilter = ColorFilter.tint(
                                md_theme_light_primary
                            ),
                            modifier = Modifier.size(24.dp)
                        )

                        AppText(
                            text = data.date,
                            textStyle = Typography.bodySmall(),
                            color = Color.Gray
                        )
                    }

                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    )

                    // Content
                    AppText(
                        text = data.content,
                        textStyle = Typography.bodyMedium(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            else -> {}
        }
    }
}