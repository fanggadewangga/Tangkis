package com.college.tangkis.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.college.tangkis.feature.login.student.LoginScreen
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.feature.onboard.OnboardScreen
import com.college.tangkis.feature.register.RegisterScreen
import com.college.tangkis.feature.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Screen.OnBoard.route) {
            OnboardScreen(navController = navController)
        }
    }
}