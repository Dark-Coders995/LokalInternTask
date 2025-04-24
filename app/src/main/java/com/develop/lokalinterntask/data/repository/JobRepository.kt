package com.develop.lokalinterntask.data.repository

import com.develop.lokalinterntask.data.api.JobApi
import com.develop.lokalinterntask.data.model.JobEntity
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

class JobRepository(private val jobApi: JobApi) {

    suspend fun getJobs(page: Int): Result<JobEntity> {
        return try {
            val jobEntity = jobApi.getJobs(page)
            Result.success(jobEntity)
        } catch (e: ClientRequestException) {
            Result.failure(Exception("Client error: ${e.response.status}"))
        } catch (e: ServerResponseException) {
            Result.failure(Exception("Server error: ${e.response.status}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
