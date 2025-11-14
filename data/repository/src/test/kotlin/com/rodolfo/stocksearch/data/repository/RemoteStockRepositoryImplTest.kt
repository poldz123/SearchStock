package com.rodolfo.stocksearch.data.repository

import com.rodolfo.stocksearch.domain.api.StockApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals


class RemoteStockRepositoryImplTest {
    private val api = mock<StockApi>()
    private val subject = RemoteStockRepositoryImpl(
        api = api
    )

    @Test
    fun `verify fetchStocks should trigger api call`() = runTest {
        whenever(api.getStocks()).thenReturn(listOf(createStock(
            ticker = "1"
        )))

        val result = subject.fetchStocks()

        assertEquals(1, result.size)
        assertEquals("1", result.first().ticker)
    }
}