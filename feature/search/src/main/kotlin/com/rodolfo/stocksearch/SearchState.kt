package com.rodolfo.stocksearch

data class SimpleUiState<DATA>(
    val data: DATA,
    val isLoading: Boolean = false,
    val isNetworkError: Boolean = false,
)