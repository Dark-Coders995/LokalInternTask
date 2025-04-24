package com.develop.lokalinterntask.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
open class BookmarkJob : RealmObject {

    @PrimaryKey
    var id: Long = 0

    var amount: String? = null
    var button_text: String = ""
    var company_name: String = ""
    var content: String = ""
    var created_on: String = ""
    var custom_link: String = ""
    var expire_on: String = ""
    var fb_shares: Int = 0

    @PersistedName("feesCharged")
    var fees_charged: Int = 0

    var fees_text: String = ""
    var is_applied: Boolean = false
    var job_category: String = ""
    var job_hours: String = ""
    var job_location_slug: String = ""
    var job_role: String = ""
    var num_applications: Int = 0
    var openings_count: Int = 0
    var other_details: String = ""
    var premium_till: String? = null
    var salary_max: Int = 0
    var salary_min: Int = 0
    var shares: Int = 0
    var title: String = ""
    var updated_on: String = ""
    var views: Int = 0
    var whatsapp_no: String = ""


    // Flattened ContactPreference fields
    var contact_preference_preference: Int = 0
    var contact_preference_end_time: String = ""
    var contact_preference_start_time: String = ""
    var contact_preference_whatsapp_link: String = ""

    // Flattened PrimaryDetails fields
    var primary_experience: String = ""
    var primary_fees_charged: String = ""
    var primary_job_type: String = ""
    var primary_place: String = ""
    var primary_qualification: String = ""
    var primary_salary: String = ""

    // JSON Serialized Lists
    var job_tags_json: String = ""
    var creatives_json: String = ""
    var V3_json: String = ""
}

fun BookmarkJob.toResultModal(): ResultModal {
    return ResultModal(
        id = id,
        amount = amount.toString(),
        button_text = button_text,
        company_name = company_name,
        content = content,
        created_on = created_on,
        custom_link = custom_link,
        expire_on = expire_on,
        fb_shares = fb_shares,
        fees_charged = fees_charged,
        fees_text = fees_text,
        is_applied = is_applied,
        job_category = job_category,
        job_hours = job_hours,
        job_location_slug = job_location_slug,
        job_role = job_role,
        num_applications = num_applications,
        openings_count = openings_count,
        other_details = other_details,
        premium_till = premium_till ?: "",
        salary_max = salary_max,
        salary_min = salary_min,
        shares = shares,
        title = title,
        updated_on = updated_on,
        views = views,
        whatsapp_no = whatsapp_no,

        contact_preference = ContactPreference(
            preference = contact_preference_preference,
            preferred_call_end_time = contact_preference_end_time,
            preferred_call_start_time = contact_preference_start_time,
            whatsapp_link = contact_preference_whatsapp_link
        ),

        primary_details = PrimaryDetails(
            Experience = primary_experience,
            Fees_Charged = primary_fees_charged,
            Job_Type = primary_job_type,
            Place = primary_place,
            Qualification = primary_qualification,
            Salary = primary_salary
        ),

        creatives = Json.decodeFromString(ListSerializer(Creative.serializer()), creatives_json),
        job_tags = Json.decodeFromString(ListSerializer(JobTag.serializer()), job_tags_json),
        contentV3 = ContentV3(
            V3 = Json.decodeFromString(ListSerializer(V3.serializer()), V3_json)
        )
    )
}



