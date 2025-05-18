package com.example.redditnews.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditnews.domain.model.Article
import com.example.redditnews.domain.usecase.GetKotlinNewsUseCase
import com.example.redditnews.presentation.intent.NewsIntent
import com.example.redditnews.presentation.state.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getKotlinNewsUseCase: GetKotlinNewsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(NewsUiState())
    val state: StateFlow<NewsUiState> = _state


    init {
        loadNews()
    }

    fun processIntent(intent: NewsIntent) {
        when (intent) {
            is NewsIntent.LoadNews -> loadNews()
        }
    }

    private fun loadNews() {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            getKotlinNewsUseCase()
                .catch { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.message ?: "Unexpected Error"
                        )
                    }
                }
                .collect { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            articles = result,
                            error = null
                        )
                    }
                }
        }
    }
}