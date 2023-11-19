package com.college.tangkis.feature.onboard

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.feature.main.components.AppButton
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary
import com.college.tangkis.theme.md_theme_light_secondary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnboardScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val pages = listOf(
        OnboardPage.First,
        OnboardPage.Second,
        OnboardPage.Third,
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<OnboardViewModel>()

    Scaffold(
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 16.dp, bottom = 32.dp)
            ) {

                // Skip
                AppText(
                    text = "Skip",
                    color = Color.Gray,
                    textStyle = Typography.labelLarge(),
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            viewModel.savePassedOnboardStatus()
                        }
                        navController.navigate(Screen.Home.route)
                    }
                )


                // Next Button
                AppButton(
                    onClick = {
                        if (pagerState.currentPage != pages.size-1)
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        else {
                            coroutineScope.launch {
                                viewModel.savePassedOnboardStatus()
                            }
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.OnBoard.route) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    modifier = Modifier.width(96.dp)
                ) {
                    AppText(
                        text = if (pagerState.currentPage != pages.size-1) "Next"
                        else "Finish",
                        color = Color.White,
                        textStyle = Typography.labelLarge()
                    )
                }
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = screenHeight / 9, end = 16.dp)
            ) {
                HorizontalPager(
                    count = pages.size,
                    state = pagerState,
                    verticalAlignment = Alignment.Top
                ) { position ->
                    PagerItem(onboardPage = pages[position])
                }
            }

            // Indicator
            HorizontalPagerIndicator(
                activeColor = md_theme_light_primary,
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = screenHeight / 5)
            )
        }
    }


}

@Composable
fun PagerItem(onboardPage: OnboardPage) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 16.dp, top = 32.dp, end = 16.dp)
    ) {
        // Onboard image
        AsyncImage(
            model = onboardPage.image,
            contentDescription = "Onboard image",
            modifier = Modifier.size(260.dp)
        )

        // Title
        Spacer(modifier = Modifier.height((screenHeight / 18).dp))
        AppText(
            text = onboardPage.title,
            textAlign = TextAlign.Center,
            textStyle = Typography.titleLarge(),
            color = md_theme_light_secondary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Description
        Spacer(modifier = Modifier.height(8.dp))
        AppText(
            text = onboardPage.description,
            textAlign = TextAlign.Center,
            textStyle = Typography.bodyLarge()
        )
    }
}