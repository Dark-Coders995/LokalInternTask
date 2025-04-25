package com.develop.lokalinterntask.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.lokalinterntask.data.model.BookmarkJob
import com.develop.lokalinterntask.data.model.JobEntity
import com.develop.lokalinterntask.data.model.ResultModal
import com.develop.lokalinterntask.data.network.NetworkResponse
import com.develop.lokalinterntask.data.repository.BookmarkRepository
import com.develop.lokalinterntask.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: JobRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    init {
        getJobs()
    }

    private val _jobs: MutableStateFlow<NetworkResponse<JobEntity>> =
        MutableStateFlow(NetworkResponse.Loading)
    val jobs: StateFlow<NetworkResponse<JobEntity>> = _jobs


    private var currentPage = 1
    var isLastPage by mutableStateOf(false)

    fun loadNextPage() {
        getJobs()
    }

    private fun getJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getJobs(currentPage)
            _jobs.value = when {
                response.isSuccessful -> {
                    response.body()?.let { jobEntity ->
                        when (jobEntity.results.size) {
                            0 -> isLastPage = true
                            else -> currentPage++
                        }
                        val currentJobs =
                            (_jobs.value as? NetworkResponse.Success)?.value?.results.orEmpty()
                        val updatedJobs = currentJobs + jobEntity.results
                        NetworkResponse.Success(jobEntity.copy(results = updatedJobs))

                    } ?: NetworkResponse.Error(Exception("No data found"))
                }

                else -> {
                    val exception = Exception(response.message())
                    NetworkResponse.Error(exception)
                }
            }
            Log.d("MainViewModel", "getJobs: ${response.body()?.results?.size ?: 0}")
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


    // <---------------------------------Bookmark Job------------------------------------>

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

    fun addBookmarkJob(bookmarkJob: BookmarkJob) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.insertBookmarkJob(bookmarkJob)
        }
    }


    private fun getAllBookmarkJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.getAllBookmarkJobs().collect {
                _bookmarkJob.value = it
            }
        }
    }


    fun removeBookmarkJob(bookmarkJob: BookmarkJob) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.deleteBookmarkJob(bookmarkJob)
        }
    }

}