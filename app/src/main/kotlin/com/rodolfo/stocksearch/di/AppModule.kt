package com.rodolfo.stocksearch.di

import com.rodolfo.stocksearch.data.network.di.networkModule
import com.rodolfo.stocksearch.data.repository.di.repositoryModule

val appModules = listOf(
    // Ui layer modules
    // Data layer modules
    networkModule,
    repositoryModule,
    // App modules
    searchModule
)