package com.college.tangkis.feature.main.navigation

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val label: String,
) {

}
