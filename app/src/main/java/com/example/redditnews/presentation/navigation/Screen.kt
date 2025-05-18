package com.example.redditnews.presentation.navigation

sealed class Screen(val route: String) {
    object NewsList : Screen("news_list")
    object NewsDetail : Screen("news_detail") {
        fun createRoute() = "news_detail"
    }
}