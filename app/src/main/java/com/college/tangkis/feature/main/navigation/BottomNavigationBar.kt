package com.college.tangkis.feature.main.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.college.tangkis.feature.main.components.AppText
import com.college.tangkis.theme.Typography
import com.college.tangkis.theme.md_theme_light_primary

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Activity,
        BottomNavigationItem.Faq,
        BottomNavigationItem.Profile,
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.LightGray,
        tonalElevation = 24.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    indicatorColor = Color.White
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        tint = if (currentRoute == item.route) md_theme_light_primary else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    AppText(
                        text = item.label,
                        textStyle = Typography.labelLarge(),
                        color = if (currentRoute == item.route) md_theme_light_primary else Color.Gray
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentRoute == item.route,
                enabled = currentRoute != item.route,
                modifier = when (item.label) {
                    "Aktivitas" -> Modifier.padding(end = 30.dp)
                    "FAQ" -> Modifier.padding(start = 30.dp)
                    else -> Modifier
                }
            )
        }
    }
}