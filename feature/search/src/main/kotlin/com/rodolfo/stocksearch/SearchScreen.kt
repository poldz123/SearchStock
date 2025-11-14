package com.rodolfo.stocksearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodolfo.stocksearch.domain.model.Stock
import com.rodolfo.stocksearch.ui.SearchTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val stocks by viewModel.selectedStocks.collectAsStateWithLifecycle()
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            SearchTopAppBar(viewModel)
            StockList(stocks)
        }
    }
}

@Composable
private fun SearchTopAppBar(
    viewModel: SearchViewModel = koinViewModel()
) {
    val query = viewModel.selectorStockSearch.collectAsStateWithLifecycle()
    SearchTopAppBar(
        query = query,
        title = {
            Text(
                text = "SearchStock",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    ) { query ->
        viewModel.search(query)
    }
}

@Composable
fun StockList(
    stocksState: SimpleUiState<List<Stock>>,
    modifier: Modifier = Modifier,
) {
    val stocks = stocksState.data
    when {
        stocksState.isNetworkError -> {
            StockListNetwork()
        }
        stocksState.isLoading && stocks.isEmpty() -> {
            StockListLoading()
        }
        stocks.isEmpty() -> {
            StockListEmpty()
        }
        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
            ) {
                itemsIndexed(stocks) { index, stock ->
                    StockItem(stock)
                }
            }
        }
    }
}

@Composable
fun StockItem(stock: Stock) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(stock.name)
        Text(stock.ticker)
        Text(stock.currentPrice.toString())
    }
    HorizontalDivider(thickness = 0.7.dp)
}

@Composable
fun StockListLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun StockListEmpty() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No Stocks Found")
    }
}

@Composable
fun StockListNetwork() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Network Error")
    }
}
