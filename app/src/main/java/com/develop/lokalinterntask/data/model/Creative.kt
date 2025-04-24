package com.develop.lokalinterntask.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Creative(
    val `file`: String,
    val image_url: String,
    val thumb_url: String
)