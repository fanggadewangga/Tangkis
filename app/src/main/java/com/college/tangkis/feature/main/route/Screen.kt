package com.college.tangkis.feature.main.route

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object OnBoard : Screen("onboard")
    data object Login : Screen("route")
    data object Register : Screen("register")
    data object StudentRoute : Screen("student_route")
}
