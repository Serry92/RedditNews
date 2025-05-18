package com.example.redditnews.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.redditnews.R
import com.example.redditnews.domain.model.Article
import com.example.redditnews.presentation.intent.NewsIntent
import com.example.redditnews.presentation.vm.NewsViewModel

@Composable
fun MainScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    onItemClick: (Article) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.processIntent(NewsIntent.LoadNews) }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.label_kotlin_news),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                when {
                    state.isLoading -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    state.error != null -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = state.error ?: "",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    state.articles.isEmpty() -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = stringResource(R.string.label_no_articles),
                                modifier = Modifier.align(Alignment.Center),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(state.articles) { article ->
                                NewsItem(
                                    article = article,
                                    onClick = {
                                        onItemClick(article)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}