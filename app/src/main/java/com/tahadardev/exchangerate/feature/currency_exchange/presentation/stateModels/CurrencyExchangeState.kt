package com.tahadardev.exchangerate.feature.currency_exchange.presentation.stateModels

data class CurrencyExchangeState (
    var currencyList : Map<String , String> = emptyMap(),
    var exchangeRates : MutableMap<String , Double> = mutableMapOf(),
    var isLoading : Boolean = false,
    var errorMsg : String = "",
    var selectedCurrency : String = "USD"
)