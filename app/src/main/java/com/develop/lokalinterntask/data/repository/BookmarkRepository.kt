package com.develop.lokalinterntask.data.repository

import com.develop.lokalinterntask.data.model.BookmarkJob
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkRepository {

    private val realm: Realm

    init {
        val config =
            RealmConfiguration.create(schema = setOf(BookmarkJob::class)) // Add other models too
        realm = Realm.open(config)
    }

    // Create
    suspend fun insertBookmarkJob(bookmarkJob: BookmarkJob) {
        realm.write {
            copyToRealm(bookmarkJob)
        }
    }

    // Read
    fun getAllBookmarkJobs(): Flow<List<BookmarkJob>> {
        return realm.query<BookmarkJob>().asFlow().map { it.list }
    }

    // Delete
    suspend fun deleteBookmarkJob(bookmarkJob: BookmarkJob) {
        realm.write {
            val job = query<BookmarkJob>("id == $0", bookmarkJob.id).first().find()
            job?.let { delete(it) }
        }
    }
}
