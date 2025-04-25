package com.develop.lokalinterntask.data.repository

import com.develop.lokalinterntask.data.model.BookmarkJob
import com.develop.lokalinterntask.data.model.BookmarkJobDao

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BookmarkRepository @Inject constructor(
    private val bookmarkJobDao: BookmarkJobDao
) {

    suspend fun insertBookmarkJob(bookmarkJob: BookmarkJob) {
        bookmarkJobDao.insertBookmarkJob(bookmarkJob)
    }

    fun getAllBookmarkJobs(): Flow<List<BookmarkJob>> = bookmarkJobDao.getAllBookmarkJobs()

    suspend fun deleteBookmarkJob(bookmarkJob: BookmarkJob) {
        bookmarkJobDao.deleteBookmarkJob(bookmarkJob)
    }

}
