package com.rodolfo.stocksearch.data.repository.di

import com.rodolfo.stocksearch.data.repository.LocalStockRepositoryImpl
import com.rodolfo.stocksearch.data.repository.RemoteStockRepositoryImpl
import com.rodolfo.stocksearch.data.repository.StockRepositoryImpl
import com.rodolfo.stocksearch.domain.api.StockRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        StockRepositoryImpl(
            remoteStockApi = get(),
            localStockApi = get()
        )
    } bind StockRepository::class

    single {
        RemoteStockRepositoryImpl(api = get())
    }

    single {
        LocalStockRepositoryImpl()
    }
}
