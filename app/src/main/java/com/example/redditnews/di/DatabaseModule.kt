package com.example.redditnews.di

import android.content.Context
import com.example.redditnews.data.datasource.local.RedditDatabase
import com.example.redditnews.data.datasource.local.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RedditDatabase {
        return RedditDatabase.getDatabase(context)
    }

    @Provides
    fun provideDao(database: RedditDatabase): ArticleDao {
        return database.articleDao()
    }
}