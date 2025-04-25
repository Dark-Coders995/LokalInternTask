package com.develop.lokalinterntask.data.repository

import com.develop.lokalinterntask.data.api.JobApi
import com.develop.lokalinterntask.data.model.JobEntity
import retrofit2.Response
import javax.inject.Inject

class JobRepository @Inject constructor(
    private val jobApi: JobApi
) {

    suspend fun getJobs(page: Int): Response<JobEntity> {
        return jobApi.getJobs(page)
    }

}