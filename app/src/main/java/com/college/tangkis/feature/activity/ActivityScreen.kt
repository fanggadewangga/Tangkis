package com.college.tangkis.feature.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.FabPosition
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.feature.consult.ConsultationViewModel
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.navigation.BottomNavigationBar
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(navController: NavController){

    val viewModel = hiltViewModel<ActivityViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppText(
                        text = "Aktivitas",
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
    ){
        val topPadding = it.calculateTopPadding()
        val bottomPadding = it.calculateBottomPadding()

        TabLayout(
            viewModel = viewModel,
            modifier = Modifier.padding(top = topPadding)
        )
    }
}

@Composable
fun TabLayout(
    viewModel: ActivityViewModel,
    modifier: Modifier
) {
    val tabIndex = viewModel.tabIndex.observeAsState()
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = tabIndex.value!!,
            backgroundColor = Color.White,
            indicator = { tabPositions ->
                val modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex.value!!])
                Box(
                    modifier = modifier
                        .height(5.dp)
                        .background(color = md_theme_light_primary, shape = RoundedCornerShape(size = 5.dp))
                )
            },
        ) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(
                    text = { AppText(text = title, color = md_theme_light_primary, textStyle = Typography.titleBold())},
                    selected = tabIndex.value!! == index,
                    onClick = { viewModel.updateTabIndex(index) },
                )
            }
        }
        when (tabIndex.value) {
            0 -> InProgressScreen(viewModel = viewModel )
            1 -> HistoryScreen(viewModel = viewModel)
        }
    }
}