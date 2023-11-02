package com.college.tangkis.feature.main.route

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object OnBoard : Screen("onboard")
    data object Login : Screen("route")
    data object Register : Screen("register")
    data object Home: Screen("home")
    data object Contact: Screen("contact")
    data object Article: Screen("article")
    data object ArticleDetail: Screen("article/{articleId}")
    data object Profile: Screen("profile")
    data object SOS: Screen("sos")
    data object SosSent: Screen("sos-sent")
    data object Report: Screen("report")
    data object Consultation: Screen("consultation")
    data object FAQ: Screen("faq")
}
