package com.college.tangkis.feature.article.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.ArticleItem
import com.college.tangkis.feature.main.components.ErrorLayout
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(navController: NavController) {

    val viewModel = hiltViewModel<ArticleViewModel>()

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
    ) {
        val topPadding = it.calculateTopPadding()

        if (viewModel.isError.value)
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                ErrorLayout(
                    image = R.drawable.iv_error,
                    message = "Daftar artikel informasi gagal ditampilkan"
                )
            }
        else
            LazyColumn(modifier = Modifier.padding(top = topPadding)) {
                items(count = 10) {
                    ArticleItem(
                        title = "Panduan Penggunaan Fitur SOS",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus in ante semper..."
                    ) {
                        navController.navigate(Screen.ArticleDetail.route)
                    }
                }
            }
    }
}