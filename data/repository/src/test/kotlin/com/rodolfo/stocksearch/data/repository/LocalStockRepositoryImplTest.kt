package com.rodolfo.stocksearch.data.repository

import com.rodolfo.stocksearch.domain.model.Stock
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LocalStockRepositoryImplTest {
    private val subject = LocalStockRepositoryImpl()

    @Test
    fun `verify searchStocks when query is available in cache`() {
        subject.cacheSearchStocks("cache", listOf(
            createStock(ticker = "1"),
            createStock(ticker = "2"),
        ))

        val result = subject.searchStocks("cache")

        assertNotNull(result)
        assertTrue(result.size == 2)
        assertEquals("1", result.first().ticker)
        assertEquals("2", result.last().ticker)
    }

    @Test
    fun `verify searchStocks when query is NOT available in cache`() {
        val result = subject.searchStocks("cache")

        assertNull(result)
    }

    @Test
    fun `verify fetchStocks when available in cache`() {
        subject.cacheStocks(listOf(
            createStock()
        ))
        val result = subject.fetchStocks()

        assertNotNull(result)
    }

    @Test
    fun `verify fetchStocks when NOT available in cache`() {
        val result = subject.fetchStocks()

        assertNull(result)
    }
}

fun createStock(
    ticker: String = "ticker",
    name: String = "name",
    currentPrice: Double = 0.0
): Stock {
    return Stock(
        ticker = ticker,
        name = name,
        currentPrice = currentPrice
    )
}