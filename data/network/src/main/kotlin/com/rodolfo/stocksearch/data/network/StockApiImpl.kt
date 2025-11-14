package com.rodolfo.stocksearch.data.network

import com.rodolfo.stocksearch.data.network.api.RetrofitStockApi
import com.rodolfo.stocksearch.domain.api.StockApi
import com.rodolfo.stocksearch.domain.model.Stock

internal class StockApiImpl(
    private val retrofitApi: RetrofitStockApi
): StockApi {
    override suspend fun getStocks(): List<Stock> {
        return retrofitApi.getStocks()
    }
}