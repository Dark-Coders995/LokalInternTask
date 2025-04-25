package com.develop.lokalinterntask.ui.widgets


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develop.lokalinterntask.R
import com.develop.lokalinterntask.data.model.BookmarkJob
import com.develop.lokalinterntask.data.model.ResultModal


@Composable
fun JobCardBase(
    modifier: Modifier = Modifier,
    jobTitle: String,
    jobLocation: String,
    companyName: String?,
    tags: List<String>,
    onBookmarkClick: () -> Unit,
    onInfoClicked: () -> Unit,
    jobTitleRow: @Composable () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            jobTitleRow()

            companyName?.takeIf { it.isNotEmpty() }?.let {
                IconWithText(image = R.drawable.building, text = it)
            }

            IconWithText(text = jobLocation)

            Spacer(modifier = Modifier.height(16.dp))

            TagChipRow(tags = tags)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = jobTitle,
                fontSize = 14.sp,
                lineHeight = 15.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .border(BorderStroke(1.dp, LightGray), RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "\uD83D\uDCDE  Call HR",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    modifier = Modifier
                        .border(BorderStroke(1.dp, LightGray), RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .size(40.dp),
                    onClick = onInfoClicked
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Composable
fun BookmarkJobCard(
    modifier: Modifier = Modifier,
    job: BookmarkJob,
    removeBookmarkJob: (BookmarkJob) -> Unit,
    onInfoClicked: () -> Unit,
) {
    val context = LocalContext.current

    JobCardBase(
        modifier = modifier,
        jobTitle = job.title,
        jobLocation = job.job_location_slug,
        companyName = job.company_name,
        tags = job.getTags(),
        onBookmarkClick = { removeBookmarkJob(job) },
        onInfoClicked = onInfoClicked,
        jobTitleRow = {
            JobTitleWithBookmarkRow(
                job = job.toModel(),
                onBookmarkClick = { removeBookmarkJob(job) },
                context = context
            )
        }
    )
}


@Composable
fun JobCard(
    modifier: Modifier = Modifier,
    job: ResultModal,
    isBookmarked: Boolean,
    addBookmarkJob: (ResultModal) -> Unit,
    onInfoClicked: () -> Unit,
) {
    val context = LocalContext.current

    JobCardBase(
        modifier = modifier,
        jobTitle = job.title,
        jobLocation = job.job_location_slug,
        companyName = job.company_name,
        tags = job.getTags(),
        onBookmarkClick = { addBookmarkJob(job) },
        onInfoClicked = onInfoClicked,
        jobTitleRow = {
            JobTitleWithBookmarkRow(
                job = job.toModel(isBookmarked),
                onBookmarkClick = { addBookmarkJob(job) },
                context = context
            )
        }
    )
}

fun ResultModal.toModel(isBookmarked: Boolean) = JobModel(
    jobRole = job_role,
    salaryMin = salary_min,
    salaryMax = salary_max,
    isBookmarked = isBookmarked
)

fun BookmarkJob.toModel() = JobModel(
    jobRole = job_role,
    salaryMin = salary_min,
    salaryMax = salary_max,
    isBookmarked = true
)

data class JobModel(
    val jobRole: String,
    val salaryMin: Int,
    val salaryMax: Int,
    val isBookmarked: Boolean
)

@Composable
fun JobTitleWithBookmarkRow(
    job: JobModel,
    onBookmarkClick: () -> Unit,
    context: Context
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.9f)
        ) {

            Text(
                text = job.jobRole,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )

            if (job.salaryMin != 0 && job.salaryMax != 0) {
                Text(
                    text = "₹${job.salaryMin} - ${job.salaryMax}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "Depends on Experience/position",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        IconButton(
            modifier = Modifier.weight(.1f),
            onClick = {
                onBookmarkClick()
                Toast.makeText(
                    context,
                    if (!job.isBookmarked) "Bookmark Added" else "Bookmark Removed",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
            Icon(
                painter =
                    if (job.isBookmarked) painterResource(id = R.drawable.bookmark_added)
                    else painterResource(id = R.drawable.bookmark_add),
                contentDescription = "Bookmark",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    image: Int? = null,
    text: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        if (icon != null)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        else if (image != null)
            Icon(
                painter = painterResource(id = image),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(14.dp)
            )
        if (icon != null || image != null)
            Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 12.sp
        )
    }

}


@Composable
fun TagChipItem(
    modifier: Modifier = Modifier,
    text: String
) {

    Box(
        modifier = modifier
            .background(Color.Blue, RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            //color = GoodBlue,
            lineHeight = 14.sp
        )
    }

}

fun ResultModal.getTags(): List<String> {
    return job_tags.map { it.value }
}

fun BookmarkJob.getTags(): List<String> {
    return job_tags.map { it.toString() }
}

@Composable
fun TagChipRow(
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        tags.forEach {
            TagChipItem(text = it)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(message)
    }
}

