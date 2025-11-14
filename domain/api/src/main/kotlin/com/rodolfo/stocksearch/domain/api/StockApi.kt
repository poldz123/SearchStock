package com.rodolfo.stocksearch.domain.api

import com.rodolfo.stocksearch.domain.model.Stock

interface StockApi {
    suspend fun getStocks(): List<Stock>
}