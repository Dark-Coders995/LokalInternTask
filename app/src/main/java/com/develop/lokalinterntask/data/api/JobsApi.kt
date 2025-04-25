package com.develop.lokalinterntask.data.api


import com.develop.lokalinterntask.data.model.JobEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApi {

    @GET("/common/jobs")
    suspend fun getJobs(
        @Query("page") page: Int = 1,
    ): Response<JobEntity>

}

