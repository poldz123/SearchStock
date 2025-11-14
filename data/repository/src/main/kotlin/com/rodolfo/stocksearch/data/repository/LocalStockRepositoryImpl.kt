package com.rodolfo.stocksearch.data.repository

import com.rodolfo.stocksearch.domain.model.Stock

internal class LocalStockRepositoryImpl() {
    private var stockCache: List<Stock>? = null
    private val searchStockCache: MutableMap<String, List<Stock>> = mutableMapOf()

    fun searchStocks(query: String): List<Stock>? {
        return searchStockCache[query]
    }

    fun cacheSearchStocks(query: String, filteredStocks: List<Stock>) {
        searchStockCache[query] = filteredStocks
    }

    fun fetchStocks(): List<Stock>? {
        return stockCache
    }

    fun cacheStocks(remoteStocks: List<Stock>) {
        stockCache = remoteStocks
    }
}