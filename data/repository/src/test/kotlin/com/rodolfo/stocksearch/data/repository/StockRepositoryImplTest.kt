package com.rodolfo.stocksearch.data.repository

import com.rodolfo.stocksearch.domain.api.NetworkState
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class StockRepositoryImplTest {
    private val remoteApiStock = mock<RemoteStockRepositoryImpl>()
    private val localApiStock = mock<LocalStockRepositoryImpl>()

    private val subject = StockRepositoryImpl(
        remoteStockApi = remoteApiStock,
        localStockApi = localApiStock
    )

    @Test
    fun `verify searchStocks should use local cache when available`() = runTest {
        whenever(localApiStock.searchStocks("cache")).thenReturn(
            listOf(
                createStock(
                    ticker = "1"
                )
            )
        )

        val result = subject.searchStocks("cache")

        assertEquals(
            NetworkState.Success(
                listOf(
                    createStock(
                        ticker = "1"
                    )
                )
            ), result
        )
    }

    @Test
    fun `verify searchStocks should use remote cache when cache is NOT available`() = runTest {
        whenever(localApiStock.searchStocks("cache")).thenReturn(null)
        whenever(localApiStock.fetchStocks()).thenReturn(null)
        whenever(remoteApiStock.fetchStocks()).thenReturn(
            listOf(
                createStock(
                    ticker = "1",
                    name = "cache"
                )
            )
        )

        val result = subject.searchStocks("cache")

        assertEquals(
            NetworkState.Success(
                listOf(
                    createStock(
                        ticker = "1",
                        name = "cache"
                    )
                )
            ), result
        )
    }

    @Test
    fun `verify searchStocks should filter stocks based on the query`() = runTest {
        whenever(localApiStock.searchStocks("cache4")).thenReturn(null)
        whenever(localApiStock.fetchStocks()).thenReturn(null)
        whenever(remoteApiStock.fetchStocks()).thenReturn(
            listOf(
                createStock(
                    ticker = "1",
                    name = "cache1"
                ),
                createStock(
                    ticker = "2",
                    name = "cache2"
                ),
                createStock(
                    ticker = "3",
                    name = "cache3"
                ),
                createStock(
                    ticker = "4",
                    name = "cache4"
                )
            )
        )

        val result = subject.searchStocks("cache4")

        assertEquals(
            NetworkState.Success(
                listOf(
                    createStock(
                        ticker = "4",
                        name = "cache4"
                    )
                )
            ), result
        )
    }
}