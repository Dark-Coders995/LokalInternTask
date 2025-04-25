package com.develop.lokalinterntask.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.develop.lokalinterntask.ui.navigation.NavGraph
import com.develop.lokalinterntask.ui.navigation.NavRoutes
import com.develop.lokalinterntask.ui.widgets.BottomBar

@Composable
fun LokalApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Box {
            NavGraph(innerPadding = innerPadding, navController = navController)

            val currentRoute = backStackEntry?.destination?.route
            when (currentRoute) {
                NavRoutes.JobDetail.route,
                NavRoutes.BookmarkDetail.route
                    -> return@Box
            }
        }
    }
}