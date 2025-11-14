package com.rodolfo.stocksearch.domain.api

import com.rodolfo.stocksearch.domain.model.Stock

interface StockRepository {
    suspend fun searchStocks(query: String): NetworkState<List<Stock>>
}

sealed class NetworkState<out T> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : NetworkState<Nothing>()
}