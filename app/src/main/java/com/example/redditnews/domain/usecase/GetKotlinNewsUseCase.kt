package com.example.redditnews.domain.usecase

import com.example.redditnews.domain.model.Article
import com.example.redditnews.domain.repo.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.onEach
import java.io.IOException
import javax.inject.Inject

class GetKotlinNewsUseCase @Inject constructor(
    private val repo: AppRepository,
) {
    suspend operator fun invoke(): Flow<List<Article>> {
        return repo.fetchRemoteNews()
            .onEach { articles ->
                repo.cacheNews(articles)
            }
            .catch { throwable ->
                if (throwable is IOException) {
                    emitAll(repo.getCachedNews())
                } else {
                    throw throwable
                }
            }
    }
}