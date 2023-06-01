package com.tahadardev.exchangerate.feature.currency_exchange.presentation.screens.stateModels.exchangeRates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tahadardev.exchangerate.feature.currency_exchange.presentation.CurrencyViewModel
import com.tahadardev.exchangerate.feature.currency_exchange.presentation.ui.theme.ExchangeRateTheme

@Composable
fun ExchangeRateScreen(
    viewModel: CurrencyViewModel = hiltViewModel()
) {
    val state = viewModel.currencyExchangeState.value
    val currencies = state.currencyList
    val exchangeRates = state.exchangeRates


    Box(modifier = Modifier.fillMaxSize()) {
        if (currencies.isNotEmpty() && exchangeRates.isNotEmpty()) {
            LazyColumn {
                item {
                    HeaderSection(currencies.keys.toMutableList())

                    Divider()
                }

                items(exchangeRates.size) { index ->
                    val key = exchangeRates.keys.elementAt(index)
                    val rate = exchangeRates[key]
                    CurrencyExchangeRateItem(key, rate!!)
                }
            }
        } else if (state.errorMsg.isNotBlank()) {
            Text(
                text = state.errorMsg,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        } else if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangeRatePrev() {
    ExchangeRateTheme {
        ExchangeRateScreen()
    }
}