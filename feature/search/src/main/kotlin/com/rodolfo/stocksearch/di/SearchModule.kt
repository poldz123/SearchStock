package com.rodolfo.stocksearch.di

import com.rodolfo.stocksearch.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel {
        SearchViewModel(
            stockRepository = get()
        )
    }
}