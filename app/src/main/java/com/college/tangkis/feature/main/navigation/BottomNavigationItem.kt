package com.college.tangkis.feature.main.navigation

import com.college.tangkis.R
import com.college.tangkis.feature.main.route.Screen

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val label: String,
) {
    data object Home: BottomNavigationItem(route = Screen.Home.route, icon = R.drawable.ic_home, label = "Beranda")
    data object Activity: BottomNavigationItem(route = Screen.Activity.route, icon = R.drawable.ic_activity, label = "Aktivitas")
    data object Faq: BottomNavigationItem(route = Screen.FAQ.route, icon = R.drawable.ic_faq, label = "FAQ")
    data object Profile: BottomNavigationItem(route = Screen.Profile.route, icon = R.drawable.ic_profile, label = "Profil")
}
