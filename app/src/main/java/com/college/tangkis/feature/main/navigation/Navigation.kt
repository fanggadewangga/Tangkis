package com.college.tangkis.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.college.tangkis.feature.article.detail.ArticleDetailScreen
import com.college.tangkis.feature.article.list.ArticleScreen
import com.college.tangkis.feature.consult.ConsultationScreen
import com.college.tangkis.feature.contact.ContactScreen
import com.college.tangkis.feature.home.HomeScreen
import com.college.tangkis.feature.login.LoginScreen
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.feature.onboard.OnboardScreen
import com.college.tangkis.feature.profile.ProfileScreen
import com.college.tangkis.feature.register.RegisterScreen
import com.college.tangkis.feature.report.ReportScreen
import com.college.tangkis.feature.sos.SosScreen
import com.college.tangkis.feature.sos.SosSentScreen
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

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Contact.route) {
            ContactScreen(navController = navController)
        }
        
        composable(route = Screen.Article.route) {
            ArticleScreen(navController = navController)
        }
        
        composable(route = Screen.ArticleDetail.route) {
            ArticleDetailScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        
        composable(route = Screen.SOS.route) {
            SosScreen(navController = navController)
        }

        composable(route = Screen.SosSent.route) {
            SosSentScreen(navController = navController)
        }
        
        composable(route = Screen.Report.route) {
            ReportScreen(navController = navController)
        }

        composable(route = Screen.Consultation.route) {
            ConsultationScreen(navController = navController)
        }
    }
}