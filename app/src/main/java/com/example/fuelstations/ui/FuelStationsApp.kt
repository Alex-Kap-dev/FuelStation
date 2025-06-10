package com.example.fuelstations.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
// Заменяем на доступные иконки
import androidx.compose.material.icons.filled.LocationOn
// или можно использовать другие подходящие иконки:
// import androidx.compose.material.icons.filled.Place
// import androidx.compose.material.icons.filled.Explore
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fuelstations.R
import com.example.fuelstations.ui.screens.MapScreen
import com.example.fuelstations.ui.screens.StationsListScreen

@Composable
fun FuelStationsApp() {
    val navController = rememberNavController()

    val items = listOf(
        Screen.MapTab,
        Screen.Stations
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.MapTab.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.MapTab.route) { MapScreen() }
            composable(Screen.Stations.route) { StationsListScreen() }
        }
    }
}

sealed class Screen(val route: String, val resourceId: Int, val icon: ImageVector) {
    // Используем Icons.Filled.LocationOn вместо Map
    object MapTab : Screen("map", R.string.map, Icons.Filled.LocationOn)
    object Stations : Screen("stations", R.string.stations, Icons.Filled.List)
}
