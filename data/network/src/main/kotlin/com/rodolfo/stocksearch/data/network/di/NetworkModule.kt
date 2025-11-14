package com.rodolfo.stocksearch.data.network.di

import com.rodolfo.stocksearch.data.network.StockApiImpl
import com.rodolfo.stocksearch.data.network.api.RetrofitStockApi
import com.rodolfo.stocksearch.domain.api.StockApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private const val STOCK_API_URL = "https://gist.githubusercontent.com/"

val networkModule = module {
    single {
        Json { ignoreUnknownKeys = true }
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(
                get<Json>().asConverterFactory("application/json".toMediaType()),
            )
    }

    single<RetrofitStockApi> {
        val client = get<OkHttpClient>()
            .newBuilder()
            .build()
        get<Retrofit.Builder>()
            .client(client)
            .baseUrl(STOCK_API_URL)
            .build()
            .create(RetrofitStockApi::class.java)
    }

    single {
        StockApiImpl(
            retrofitApi = get(),
        )
    } bind StockApi::class
}
