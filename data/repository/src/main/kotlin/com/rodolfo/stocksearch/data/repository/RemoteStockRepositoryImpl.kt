package com.rodolfo.stocksearch.data.repository

import com.rodolfo.stocksearch.domain.api.StockApi
import com.rodolfo.stocksearch.domain.model.Stock

internal class RemoteStockRepositoryImpl(
    private val api: StockApi
) {
    suspend fun fetchStocks(): List<Stock> {
        return api.getStocks()
    }
}