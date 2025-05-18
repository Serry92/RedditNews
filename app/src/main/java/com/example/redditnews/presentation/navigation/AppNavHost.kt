package com.example.redditnews.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.redditnews.domain.model.Article
import com.example.redditnews.presentation.ui.DetailsScreen
import com.example.redditnews.presentation.ui.MainScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val articleState = remember { mutableStateOf<Article?>(null) }
    NavHost(navController = navController, startDestination = Screen.NewsList.route) {
        composable(Screen.NewsList.route) {
            MainScreen { article ->
                articleState.value = article
                navController.navigate(Screen.NewsDetail.createRoute())
            }
        }
        composable(Screen.NewsDetail.route) { backStackEntry ->
            articleState.value?.let {
                DetailsScreen(article = it) {
                    navController.popBackStack()
                }

            }
        }
    }
}