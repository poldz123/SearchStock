package com.rodolfo.stocksearch

import android.app.Application
import com.rodolfo.stocksearch.data.network.BuildConfig
import com.rodolfo.stocksearch.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration
import timber.log.Timber

@OptIn(KoinExperimentalAPI::class)
class StockApplication : Application(), KoinStartup {

    override fun onKoinStartup() = koinConfiguration {
        androidLogger()
        androidContext(this@StockApplication)
        modules(
            buildList {
                addAll(appModules)
            }
        )
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}