package com.tahadardev.exchangerate.feature.currency_exchange.presentation.screens.stateModels

data class CurrencyExchangeState (
    var currencyList : Map<String , String> = emptyMap(),
    var exchangeRates : MutableMap<String , Double> = mutableMapOf(),
    var isLoading : Boolean = false,
    var errorMsg : String = "",
    var selectedCurrency : String = "USD"
)