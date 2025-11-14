package com.rodolfo.stocksearch.data.repository

import com.rodolfo.stocksearch.domain.api.NetworkState
import com.rodolfo.stocksearch.domain.api.StockRepository
import com.rodolfo.stocksearch.domain.model.Stock

internal class StockRepositoryImpl(
    val remoteStockApi: RemoteStockRepositoryImpl,
    val localStockApi: LocalStockRepositoryImpl
) : StockRepository {

    override suspend fun searchStocks(query: String): NetworkState<List<Stock>> {
        return try {
            val localStocks = localStockApi.searchStocks(query)
            val result = if (localStocks == null) {
                val stocks = localStockApi.fetchStocks() ?: remoteStockApi.fetchStocks().also {
                    localStockApi.cacheStocks(it)
                }
                filterStocks(query, stocks).also { filteredStocks ->
                    localStockApi.cacheSearchStocks(query, filteredStocks)
                }
            } else {
                localStocks
            }
            NetworkState.Success(result)
        } catch (exception: Exception) {
            NetworkState.Error(exception.message.orEmpty(), exception)
        }
    }

    private fun filterStocks(query: String, remoteStocks: List<Stock>): List<Stock> {
        return remoteStocks.filter { stock ->
            stock.ticker.contains(query, ignoreCase = true) ||
                    stock.name.contains(query, ignoreCase = true)
        }.sortedWith(
            compareBy { stock ->
                when {
                    stock.ticker.equals(query, ignoreCase = true) -> 1
                    stock.name.equals(query, ignoreCase = true) -> 2
                    else -> 3
                }
            }
        )
    }
}