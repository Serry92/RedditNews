package com.example.redditnews.domain.repo

import com.example.redditnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun fetchRemoteNews(): Flow<List<Article>>
    suspend fun getCachedNews(): Flow<List<Article>>
    suspend fun cacheNews(articles: List<Article>)
}