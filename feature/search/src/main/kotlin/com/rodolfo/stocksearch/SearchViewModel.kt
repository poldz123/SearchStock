package com.rodolfo.stocksearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodolfo.stocksearch.domain.api.NetworkState
import com.rodolfo.stocksearch.domain.api.StockRepository
import com.rodolfo.stocksearch.domain.model.Stock
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val stockRepository: StockRepository
) : ViewModel() {

    private var fetchStocksJob: Job? = null

    private val _selectedStockSearch = MutableStateFlow("")
    private val _selectedStocks = MutableStateFlow(
        SimpleUiState(
            data = emptyList<Stock>(),
            isLoading = false
        )
    )

    val selectorStockSearch: StateFlow<String> = _selectedStockSearch
    val selectedStocks: StateFlow<SimpleUiState<List<Stock>>> = _selectedStocks

    init {
        fetchStocks()
    }

    fun search(query: String) {
        _selectedStockSearch.tryEmit(query)
    }

    fun fetchStocks() {
        fetchStocksJob?.cancel()
        fetchStocksJob = viewModelScope.launch {
            _selectedStockSearch.collectLatest { query ->
                _selectedStocks.update {
                    it.copy(
                        isNetworkError = false,
                        isLoading = true,
                    )
                }
                val stocks = stockRepository.searchStocks(query)
                when (stocks) {
                    is NetworkState.Success -> {
                        _selectedStocks.emit(SimpleUiState(
                            data = stocks.data
                        ))
                    }
                    is NetworkState.Error -> {
                        _selectedStocks.update {
                            it.copy(
                                isNetworkError = true,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}

