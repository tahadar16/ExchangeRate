package com.tahadardev.exchangerate.feature.currency_exchange.presentation

data class CurrencyExchangeState (
    var currencyList : Map<String , String> = emptyMap(),
    var exchangeRates : Map<String , Double> = emptyMap(),
    var isLoading : Boolean = false,
    var errorMsg : String = "",
    var selectedCurrency : String = "USD"
)