package com.develop.lokalinterntask.data.model

import kotlinx.serialization.Serializable


@Serializable
data class JobEntity(
    val results: List<ResultModal>
)