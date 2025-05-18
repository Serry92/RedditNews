package com.example.redditnews.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.redditnews.data.datasource.local.dao.ArticleDao
import com.example.redditnews.data.datasource.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: RedditDatabase? = null

        fun getDatabase(context: Context): RedditDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RedditDatabase::class.java,
                    "reddit_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}