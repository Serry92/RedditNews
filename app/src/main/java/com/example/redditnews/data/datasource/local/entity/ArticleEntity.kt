package com.example.redditnews.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.redditnews.domain.model.Article


@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val title: String,
    val imageUrl: String?,
    val body: String?,
    val articleUrl: String,
)

fun ArticleEntity.toArticle() = Article(title, imageUrl, body, articleUrl)
fun Article.toEntity() = ArticleEntity(title, imageUrl, body, articleUrl)
