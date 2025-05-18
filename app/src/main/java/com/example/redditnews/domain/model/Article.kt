package com.example.redditnews.domain.model

data class Article(
    val title: String,
    val imageUrl: String?,
    val body: String?,
    val articleUrl: String
)