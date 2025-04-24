package com.develop.lokalinterntask.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactPreference(
    var preference: Int,
    var preferred_call_end_time: String,
    var preferred_call_start_time: String,
    var whatsapp_link: String
)
