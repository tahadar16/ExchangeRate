package com.tahadardev.exchangerate.feature.currency_exchange.domain.model

data class ExchangeRates(
    var rates : MutableMap<String, Double> = hashMapOf(),
    private var timestamp : Int
)
