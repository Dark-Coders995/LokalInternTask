package com.develop.lokalinterntask.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
data class ResultModal(
    val id: Long,
    val amount: String,
    val button_text: String,
    val company_name: String,
    val content: String,
    val created_on: String,
    val custom_link: String,
    val expire_on: String,
    val fb_shares: Int,
    val fees_charged: Int,
    val fees_text: String,
    val is_applied: Boolean,
    val job_category: String,
    val job_hours: String,
    val job_location_slug: String,
    val job_role: String,
    val num_applications: Int,
    val openings_count: Int,
    val other_details: String,
    val premium_till: String,
    val salary_max: Int,
    val salary_min: Int,
    val shares: Int,
    val title: String,
    val updated_on: String,
    val views: Int,
    val whatsapp_no: String,

    val contact_preference: ContactPreference,
    val primary_details: PrimaryDetails,
    val job_tags: List<JobTag>,
    val creatives: List<Creative>,
    val contentV3: ContentV3,
) {

    fun isValid(): Boolean {
        return this.id != 0L
    }

}

fun ResultModal.toBookmarkJob(): BookmarkJob {
    return BookmarkJob().apply {
        id = this@toBookmarkJob.id
        amount = this@toBookmarkJob.amount
        button_text = this@toBookmarkJob.button_text
        company_name = this@toBookmarkJob.company_name
        content = this@toBookmarkJob.content
        created_on = this@toBookmarkJob.created_on
        custom_link = this@toBookmarkJob.custom_link
        expire_on = this@toBookmarkJob.expire_on
        fb_shares = this@toBookmarkJob.fb_shares
        fees_charged = this@toBookmarkJob.fees_charged
        fees_text = this@toBookmarkJob.fees_text
        is_applied = this@toBookmarkJob.is_applied
        job_category = this@toBookmarkJob.job_category
        job_hours = this@toBookmarkJob.job_hours
        job_location_slug = this@toBookmarkJob.job_location_slug
        job_role = this@toBookmarkJob.job_role
        num_applications = this@toBookmarkJob.num_applications
        openings_count = this@toBookmarkJob.openings_count
        other_details = this@toBookmarkJob.other_details
        premium_till = this@toBookmarkJob.premium_till
        salary_max = this@toBookmarkJob.salary_max
        salary_min = this@toBookmarkJob.salary_min
        shares = this@toBookmarkJob.shares
        title = this@toBookmarkJob.title
        updated_on = this@toBookmarkJob.updated_on
        views = this@toBookmarkJob.views
        whatsapp_no = this@toBookmarkJob.whatsapp_no

        contact_preference_preference = contact_preference.preference
        contact_preference_end_time = contact_preference.preferred_call_end_time
        contact_preference_start_time = contact_preference.preferred_call_start_time
        contact_preference_whatsapp_link = contact_preference.whatsapp_link

        primary_experience = primary_details.Experience
        primary_fees_charged = primary_details.Fees_Charged
        primary_job_type = primary_details.Job_Type
        primary_place = primary_details.Place
        primary_qualification = primary_details.Qualification
        primary_salary = primary_details.Salary

        job_tags_json = Json.encodeToString(ListSerializer(JobTag.serializer()), job_tags)
        creatives_json = Json.encodeToString(ListSerializer(Creative.serializer()), creatives)
        V3_json = Json.encodeToString(ListSerializer(V3.serializer()), contentV3.V3)
    }
}

