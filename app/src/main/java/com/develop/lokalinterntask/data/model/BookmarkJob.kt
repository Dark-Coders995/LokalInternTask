package com.develop.lokalinterntask.data.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "bookmark_job")
data class BookmarkJob(
    @PrimaryKey val id: Long,
    val amount: String?,
    val button_text: String,
    val company_name: String,
    val content: String,
    val created_on: String,
    val custom_link: String,
    val expire_on: String,
    val fb_shares: Int,
    @ColumnInfo(name = "feesCharged") val fees_charged: Int,
    val fees_text: String,
    val is_applied: Boolean,
    val job_category: String,
    val job_hours: String,
    val job_location_slug: String,
    val job_role: String,
    val num_applications: Int,
    val openings_count: Int,
    val other_details: String,
    val premium_till: String?,
    val salary_max: Int,
    val salary_min: Int,
    val shares: Int,
    val title: String,
    val updated_on: String,
    val views: Int,
    val whatsapp_no: String,

    @Embedded
    val contact_preference: ContactPreference,
    @Embedded
    val primary_details: PrimaryDetails,

    val V3: List<V3>,          // These will be stored as JSON strings
    val creatives: List<Creative>,
    val job_tags: List<JobTag>

//    // ContactPreference
//    val ContactPreference_preference: Int,
//    val ContactPreference_preferred_call_end_time: String,
//    val ContactPreference_preferred_call_start_time: String,
//    val ContactPreference_whatsapp_link: String,
//
//    // PrimaryDetails
//    val PrimaryDetails_Experience: String,
//    val PrimaryDetails_Fees_Charged: String,
//    val PrimaryDetails_Job_Type: String,
//    val PrimaryDetails_Place: String,
//    val PrimaryDetails_Qualification: String,
//    val PrimaryDetails_Salary: String
//
//    //

)

@Dao
interface BookmarkJobDao {

    // CRUD operations for BookmarkJob
    // Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmarkJob(bookmarkJob: BookmarkJob)

    // Read

    @Query("SELECT * FROM bookmark_job")
    fun getAllBookmarkJobs(): Flow<List<BookmarkJob>>

    // Delete
    @Delete
    suspend fun deleteBookmarkJob(bookmarkJob: BookmarkJob)

}