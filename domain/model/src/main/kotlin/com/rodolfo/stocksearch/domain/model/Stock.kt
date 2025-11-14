package com.rodolfo.stocksearch.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val ticker: String,
    val name: String,
    val currentPrice: Double
)