package com.develop.lokalinterntask.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.develop.lokalinterntask.data.model.BookmarkJob
import com.develop.lokalinterntask.data.model.JobEntity
import com.develop.lokalinterntask.data.model.ResultModal
import com.develop.lokalinterntask.data.network.NetworkResponse
import com.develop.lokalinterntask.ui.navigation.NavRoutes
import com.develop.lokalinterntask.ui.widgets.ErrorScreen
import com.develop.lokalinterntask.ui.widgets.JobCard
import com.develop.lokalinterntask.viewmodel.MainViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModal: MainViewModel
) {

    val jobs: NetworkResponse<JobEntity> by viewModal.jobs.collectAsState()

    when (val jobResponse = jobs) {
        is NetworkResponse.Loading -> {
            LoadingScreen()
        }

        is NetworkResponse.Error -> {
            ErrorScreen(message = jobResponse.exception.message ?: "Error")
        }

        is NetworkResponse.Success -> {
            JobListScreen(
                jobs = jobResponse.value.results,
                bookmarkJobsFlow = viewModal.bookmarkJob,
                navController = navController,
                isLastPage = viewModal.isLastPage,
                onLoadMore = { viewModal.loadNextPage() },
                addBookmarkJob = { res ->
                    val bookmarkJob = res.toBookmarkJob()
                    viewModal.addBookmarkJob(bookmarkJob)
                }
            )
        }
    }

}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            Text("Loading", textAlign = TextAlign.Center)
        }
    }

}

@Composable
private fun JobListScreen(
    modifier: Modifier = Modifier,
    jobs: List<ResultModal>,
    bookmarkJobsFlow: StateFlow<List<BookmarkJob>>,
    navController: NavController,
    isLastPage: Boolean,
    onLoadMore: () -> Unit,
    addBookmarkJob: (ResultModal) -> Unit
) {

    val bookmarkJobs by bookmarkJobsFlow.collectAsState()

    Box(modifier = modifier) {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 70.dp)
        ) {

            item {
                Text(
                    text = "Lokal Jobs",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(jobs) { job: ResultModal ->

                if (job.isValid())
                    JobCard(
                        modifier = Modifier, job = job,
                        onInfoClicked = {
                            navController.navigate(NavRoutes.JobDetail.passJobID(job.id))
                        },
                        isBookmarked = bookmarkJobs.any { it.id == job.id },
                        addBookmarkJob = addBookmarkJob
                    )

            }
            item {
                onLoadMore()

                when {
                    isLastPage -> {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No more jobs", style = MaterialTheme.typography.bodyLarge)
                        }
                    }

                    else -> {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))

            }
        }


    }
}
