package com.example.redditnews.presentation.intent

import com.example.redditnews.domain.model.Article

sealed class NewsIntent {
    object LoadNews : NewsIntent()
}