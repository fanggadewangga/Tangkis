package com.college.tangkis.feature.main.navigation

import com.college.tangkis.R

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val label: String,
) {
    data object Home: BottomNavigationItem(route = "home", icon = R.drawable.ic_home, label = "Beranda")
    data object Activity: BottomNavigationItem(route = "activity", icon = R.drawable.ic_activity, label = "Aktivitas")
    data object Faq: BottomNavigationItem(route = "faq", icon = R.drawable.ic_faq, label = "FAQ")
    data object Profile: BottomNavigationItem(route = "profile", icon = R.drawable.ic_profile, label = "Profil")
}
