package com.example.redditnews.presentation.state

import com.example.redditnews.domain.model.Article

data class NewsUiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null,
)