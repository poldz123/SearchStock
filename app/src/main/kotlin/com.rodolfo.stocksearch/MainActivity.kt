package com.rodolfo.stocksearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rodolfo.stocksearch.ui.StockApp
import com.rodolfo.stocksearch.ui.StockTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StockTheme(isDarkTheme = false) {
                StockApp()
            }
        }
    }
}
