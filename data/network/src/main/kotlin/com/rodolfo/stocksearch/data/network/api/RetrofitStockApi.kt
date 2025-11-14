package com.rodolfo.stocksearch.data.network.api

import com.rodolfo.stocksearch.domain.model.Stock
import retrofit2.http.GET

internal interface RetrofitStockApi {

    @GET("priyanshrastogi/0e1d4f8d517698cfdced49f5e59567be/raw/9158ad254e92aaffe215e950f4846a23a0680703/mock-stocks.json")
    suspend fun getStocks(): List<Stock>
}