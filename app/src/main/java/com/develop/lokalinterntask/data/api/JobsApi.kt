package com.develop.lokalinterntask.data.api


import com.develop.lokalinterntask.data.model.JobEntity
import com.develop.lokalinterntask.utils.KtorClient.client
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface JobApi {
    suspend fun getJobs(page: Int): JobEntity
}


class JobApiImpl : JobApi {

    private val BASE_URL = "https://testapi.getlokalapp.com"

    override suspend fun getJobs(page: Int): JobEntity {
        return client.get("$BASE_URL/common/jobs") {
            parameter("page", page)
        }.body()
    }
}
