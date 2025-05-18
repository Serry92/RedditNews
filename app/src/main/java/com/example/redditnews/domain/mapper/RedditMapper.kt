package com.example.redditnews.domain.mapper

import com.example.redditnews.data.datasource.remote.dto.RedditPostDto
import com.example.redditnews.domain.model.Article

fun RedditPostDto.toArticle(): Article = Article(
    title = title,
    imageUrl = media?.oembed?.thumbnail_url.takeIf { it?.startsWith("http") == true },
    body = selftext,
    articleUrl = url
)