package com.tahadardev.exchangerate.feature.currency_exchange.presentation.screens.stateModels.exchangeRates

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tahadardev.exchangerate.feature.currency_exchange.presentation.ui.theme.ExchangeRateTheme

@Composable
fun ExchangeRateScreen() {
    LazyColumn {
        item {
            HeaderSection(mutableListOf("USD", "RS", "YN", "SRY"))
        }
    }
}

@Preview
@Composable
fun ExchangeRatePrev() {
    ExchangeRateTheme {
        ExchangeRateScreen()
    }
}