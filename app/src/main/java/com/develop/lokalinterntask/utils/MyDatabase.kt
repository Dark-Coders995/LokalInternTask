package com.develop.lokalinterntask.utils


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.develop.lokalinterntask.data.model.BookmarkJob
import com.develop.lokalinterntask.data.model.BookmarkJobDao

@Database(entities = [BookmarkJob::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun bookmarkJobDao(): BookmarkJobDao
}