package com.college.tangkis.feature.main.navigation

import com.college.tangkis.R

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val label: String,
) {
    data object Home: BottomNavigationItem(route = "home", icon = R.drawable.ic_home, label = "Beranda")
    data object Profile: BottomNavigationItem(route = "profile", icon = R.drawable.ic_profile, label = "Profil")
}
