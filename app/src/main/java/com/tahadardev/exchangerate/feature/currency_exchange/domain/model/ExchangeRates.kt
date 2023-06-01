package com.tahadardev.exchangerate.feature.currency_exchange.domain.model

data class ExchangeRates(
    var rates : Map<String, Double>,
    private var timestamp : Int
)
