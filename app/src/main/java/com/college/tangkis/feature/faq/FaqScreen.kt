package com.college.tangkis.feature.faq

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.components.FaqItem
import com.college.tangkis.feature.main.navigation.BottomNavigationBar
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(navController: NavController) {

    val viewModel = hiltViewModel<FaqViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Frequently Asked Questions",
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.SOS.route) },
                shape = CircleShape,
                containerColor = md_theme_light_secondary,
                modifier = Modifier.size(80.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_sos,
                    contentDescription = "SOS",
                    modifier = Modifier.size(42.dp)
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                tonalElevation = 8.dp,
                containerColor = Color.White,
            ) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        LazyColumn(
            modifier = Modifier
                .padding(
                    top = topPadding + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = bottomPadding + 8.dp
                ),
        ) {
            items(viewModel.listFaq) { faq ->
                FaqItem(faq = faq, modifier = Modifier.padding(bottom = 16.dp))
            }
        }
    }
}