package com.college.tangkis.feature.splash


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.college.tangkis.R
import com.college.tangkis.core.util.Constants.SPLASH_TIME
import com.college.tangkis.feature.main.route.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = hiltViewModel<SplashViewModel>()

    LaunchedEffect(true) {
        delay(SPLASH_TIME)
        if (viewModel.isPassedOnboard.value)
            if (viewModel.token.value.isEmpty())
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            else
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
        else
            navController.navigate(Screen.OnBoard.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        AsyncImage(
            model = R.drawable.iv_app_logo,
            contentDescription = "App logo",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}