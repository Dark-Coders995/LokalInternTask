package com.develop.lokalinterntask.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.develop.lokalinterntask.ui.screens.BookmarkDetailScreen
import com.develop.lokalinterntask.ui.screens.BookmarkScreen
import com.develop.lokalinterntask.ui.screens.JobDetailScreen
import com.develop.lokalinterntask.ui.screens.JobScreen
import com.develop.lokalinterntask.ui.widgets.ErrorScreen
import com.develop.lokalinterntask.viewmodel.MainViewModel

@Composable
fun NavGraph(
    innerPadding: PaddingValues,
    navController: NavHostController
) {


    val viewModel: MainViewModel = hiltViewModel()


    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = NavRoutes.Job.route
    ) {

        composable(NavRoutes.Job.route) {
            JobScreen(viewModal = viewModel, navController = navController)
        }

        composable(NavRoutes.Bookmark.route) {
            BookmarkScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = NavRoutes.JobDetail.route,
            arguments = listOf(
                navArgument("jobId") {
                    type = NavType.LongType
                }
            )
        ) {
            val jobId = it.arguments?.getLong("jobId")
            val job = viewModel.getJobById(jobId!!)
            job?.let {
                JobDetailScreen(job = it)
            } ?: ErrorScreen(message = "Job Id $jobId not found")

        }

        composable(
            route = NavRoutes.BookmarkDetail.route,
            arguments = listOf(navArgument("jobId") { type = NavType.LongType })
        ) { navBackStackEntry ->

            val jobId = navBackStackEntry.arguments?.getLong("jobId")
            val job = viewModel.getBookmarkJobById(jobId!!)
            job?.let {
                BookmarkDetailScreen(job = it)
            } ?: ErrorScreen(message = "Bookmarked Job $jobId not found")

        }

    }
}