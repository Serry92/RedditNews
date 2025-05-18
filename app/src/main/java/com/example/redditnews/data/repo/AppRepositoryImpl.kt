package com.example.redditnews.data.repo

import com.example.redditnews.data.datasource.local.dao.ArticleDao
import com.example.redditnews.data.datasource.local.entity.toArticle
import com.example.redditnews.data.datasource.local.entity.toEntity
import com.example.redditnews.data.datasource.remote.api.AppApi
import com.example.redditnews.domain.mapper.toArticle
import com.example.redditnews.domain.model.Article
import com.example.redditnews.domain.repo.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApi,
    private val dao: ArticleDao,
) : AppRepository {
    override suspend fun fetchRemoteNews(): Flow<List<Article>> = flow {
        emit(api.getKotlinNews().data.children.map { it.data.toArticle() })
    }

    override suspend fun getCachedNews(): Flow<List<Article>> = flow {
        val cachedArticles = dao.getAll().map { it.toArticle() }
        println("Cached articles: ${cachedArticles.size}")
        emit(cachedArticles)
    }

    override suspend fun cacheNews(articles: List<Article>) {
        dao.insertAll(articles.map { it.toEntity() })
    }
}