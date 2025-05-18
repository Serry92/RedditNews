package com.example.redditnews.di

import com.example.redditnews.data.datasource.local.RedditDatabase
import com.example.redditnews.data.datasource.local.dao.ArticleDao
import com.example.redditnews.data.datasource.remote.api.AppApi
import com.example.redditnews.data.repo.AppRepositoryImpl
import com.example.redditnews.domain.repo.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        appApi: AppApi,
        articleDao: ArticleDao,
    ): AppRepository {
        return AppRepositoryImpl(appApi, articleDao)
    }
}