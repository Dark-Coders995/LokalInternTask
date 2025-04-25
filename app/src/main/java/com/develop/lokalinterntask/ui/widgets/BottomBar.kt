package com.develop.lokalinterntask.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.develop.lokalinterntask.R
import com.develop.lokalinterntask.ui.navigation.NavRoutes


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    var selected = when (backStackEntry?.destination?.route) {
        NavRoutes.Bookmark.route -> "bookmarks"
        else -> "job"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(70.dp),
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(45.dp),
            shadowElevation = 8.dp,
            tonalElevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate(NavRoutes.Job.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Icon(
                        painter = if (selected == "job") {
                            painterResource(id = R.drawable.work)
                        } else {
                            painterResource(id = R.drawable.work_outline)
                        },
                        contentDescription = "Job",
                        modifier = Modifier.size(28.dp)
                    )
                }
                IconButton(onClick = {
                    navController.navigate(NavRoutes.Bookmark.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Icon(
                        painter = if (selected == "bookmarks") {
                            painterResource(id = R.drawable.bookmark)
                        } else {
                            painterResource(id = R.drawable.bookmark_outline)
                        },
                        contentDescription = "Bookmarks",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}
