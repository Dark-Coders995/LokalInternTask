package com.develop.lokalinterntask.di


import android.content.Context
import androidx.room.Room
import com.develop.lokalinterntask.data.model.BookmarkJobDao
import com.develop.lokalinterntask.utils.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "lokal_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBookmarkJobDao(database: MyDatabase): BookmarkJobDao = database.bookmarkJobDao()

}