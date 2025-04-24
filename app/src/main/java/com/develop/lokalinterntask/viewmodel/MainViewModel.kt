package com.develop.lokalinterntask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.lokalinterntask.data.model.BookmarkJob
import com.develop.lokalinterntask.data.model.JobEntity
import com.develop.lokalinterntask.data.model.ResultModal
import com.develop.lokalinterntask.data.model.toBookmarkJob
import com.develop.lokalinterntask.data.network.NetworkResponse
import com.develop.lokalinterntask.data.repository.BookmarkRepository
import com.develop.lokalinterntask.data.repository.JobRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val jobRepository: JobRepository,
    private val bookmarkRepository: BookmarkRepository,
) : ViewModel() {

    private val _jobs: MutableStateFlow<NetworkResponse<JobEntity>> =
        MutableStateFlow(NetworkResponse.Loading)
    val jobs: StateFlow<NetworkResponse<JobEntity>> = _jobs

    private var currentPage = 1
    var isLastPage = false

    init {
        getJobs()
    }

    fun loadNextPage() {
        getJobs()
    }

    private fun getJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            _jobs.value = try {
                val jobEntity = jobRepository.getJobs(currentPage).getOrThrow() // Fetch jobs
                if (jobEntity.results.isEmpty()) {
                    isLastPage = true
                } else {
                    currentPage++
                }
                NetworkResponse.Success(jobEntity)
            } catch (e: Exception) {
                NetworkResponse.Error(e) // Handle error state
            }
        }
    }

    fun getJobById(jobId: Long): ResultModal? {
        return when (val jobResponse = jobs.value) {
            is NetworkResponse.Success -> {
                jobResponse.value.results.find { it.id == jobId }
            }

            else -> null
        }
    }

    fun getBookmarkJobById(jobId: Long): BookmarkJob? {
        return bookmarkJob.value.find { it.id == jobId }
    }

    init {
        getAllBookmarkJobs()
    }

    fun toggleBookmarkJob(jobId: Long) {
        val job = getJobById(jobId)
        job?.let {
            val bookmarkJob = it.toBookmarkJob()
            if (getBookmarkJobById(jobId) == null) {
                addBookmarkJob(bookmarkJob)
            } else {
                removeBookmarkJob(bookmarkJob)
            }
        }
    }

    private val _bookmarkJob: MutableStateFlow<List<BookmarkJob>> = MutableStateFlow(emptyList())
    val bookmarkJob: StateFlow<List<BookmarkJob>>
        get() = _bookmarkJob

    // Crud operations for BookmarkJob
    // Create
    fun addBookmarkJob(bookmarkJob: BookmarkJob) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.insertBookmarkJob(bookmarkJob)
        }
    }

    // Read
    private fun getAllBookmarkJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.getAllBookmarkJobs().collect {
                _bookmarkJob.value = it
            }
        }
    }

    // Delete
    fun removeBookmarkJob(bookmarkJob: BookmarkJob) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.deleteBookmarkJob(bookmarkJob)
        }
    }


}
