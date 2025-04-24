package com.develop.lokalinterntask.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JobTag(
    val bg_color: String,
    val text_color: String,
    val value: String
)