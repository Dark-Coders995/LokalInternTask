package com.develop.lokalinterntask.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.develop.lokalinterntask.data.model.BookmarkJob
import com.develop.lokalinterntask.ui.navigation.NavRoutes
import com.develop.lokalinterntask.ui.widgets.BookmarkJobCard
import com.develop.lokalinterntask.ui.widgets.ErrorScreen
import com.develop.lokalinterntask.viewmodel.MainViewModel


@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {

    val bookmarkJob by viewModel.bookmarkJob.collectAsState()

    Column {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        when {
            bookmarkJob.isEmpty() -> NoBookmarkScreen()
            else -> BookmarkListScreen(modifier, bookmarkJob, navController, viewModel)
        }

    }


}

@Composable
fun NoBookmarkScreen(modifier: Modifier = Modifier) {
    ErrorScreen(message = "No bookmark found")
}

@Composable
private fun BookmarkListScreen(
    modifier: Modifier,
    bookmarkJob: List<BookmarkJob>,
    navController: NavController,
    viewModel: MainViewModel
) {
    Box(modifier = modifier) {


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 70.dp)

        ) {
            items(bookmarkJob) { job ->

                BookmarkJobCard(
                    modifier = Modifier, job = job,
                    onInfoClicked = {
                        navController.navigate(NavRoutes.BookmarkDetail.passBookmarkJobId(job.id))
                    },
                    removeBookmarkJob = { viewModel.removeBookmarkJob(it) }
                )

            }
        }
    }
}