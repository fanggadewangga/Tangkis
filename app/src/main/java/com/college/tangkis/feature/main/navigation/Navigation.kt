package com.college.tangkis.feature.main.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.college.tangkis.feature.activity.ActivityScreen
import com.college.tangkis.feature.article.detail.ArticleDetailScreen
import com.college.tangkis.feature.article.list.ArticleScreen
import com.college.tangkis.feature.changephonenumber.ChangeNumberScreen
import com.college.tangkis.feature.changepassword.ChangePasswordScreen
import com.college.tangkis.feature.consult.ConsultationDetailScreen
import com.college.tangkis.feature.consult.ConsultationScreen
import com.college.tangkis.feature.consult.SentConsultationSuccessScreen
import com.college.tangkis.feature.contact.ContactScreen
import com.college.tangkis.feature.faq.FaqScreen
import com.college.tangkis.feature.home.HomeScreen
import com.college.tangkis.feature.login.LoginScreen
import com.college.tangkis.feature.main.route.Screen
import com.college.tangkis.feature.onboard.OnboardScreen
import com.college.tangkis.feature.profile.ProfileScreen
import com.college.tangkis.feature.register.RegisterScreen
import com.college.tangkis.feature.report.ReportDetailScreen
import com.college.tangkis.feature.report.ReportScreen
import com.college.tangkis.feature.report.SentReportSuccessScreen
import com.college.tangkis.feature.sos.SosScreen
import com.college.tangkis.feature.sos.SosSentScreen
import com.college.tangkis.feature.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
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

        composable(route = Screen.ArticleDetail.route) { navBackStackEntry ->
            val articleId = navBackStackEntry.arguments?.getString("articleId")
            articleId?.let {
                ArticleDetailScreen(navController = navController, articleId = articleId)
            }
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

        composable(route = Screen.ReportDetail.route) { navBackStackEntry ->
            val reportId = navBackStackEntry.arguments?.getString("reportId")
            reportId?.let {
                ReportDetailScreen(navController = navController, reportId = reportId)
            }
        }

        composable(route = Screen.Consultation.route) {
            ConsultationScreen(navController = navController)
        }

        composable(route = Screen.ConsultationDetail.route) { navBackStackEntry ->
            val consultationId = navBackStackEntry.arguments?.getString("consultationId")
            consultationId?.let {
                ConsultationDetailScreen(
                    navController = navController,
                    consultationId = consultationId
                )
            }
        }

        composable(route = Screen.FAQ.route) {
            FaqScreen(navController = navController)
        }

        composable(route = Screen.Activity.route) {
            ActivityScreen(navController = navController)
        }

        composable(route = Screen.ChangePhoneNumber.route) {
            ChangeNumberScreen(navController = navController)
        }

        composable(route = Screen.ChangePassword.route) {
            ChangePasswordScreen(navController = navController)
        }

        composable(route = Screen.SentReportSuccess.route) { navBackStackEntry ->
            val reportId = navBackStackEntry.arguments?.getString("reportId")
            Log.d("Report Id", reportId.toString())
            reportId?.let {
                SentReportSuccessScreen(navController = navController, reportId = reportId)
            }
        }

        composable(route = Screen.SentConsultationSuccess.route) { navBackStackEntry ->
            val consultationId = navBackStackEntry.arguments?.getString("consultationId")
            Log.d("Consultation Id", consultationId.toString())
            consultationId?.let {
                SentConsultationSuccessScreen(
                    navController = navController,
                    consultationId = consultationId
                )
            }
        }
    }
}