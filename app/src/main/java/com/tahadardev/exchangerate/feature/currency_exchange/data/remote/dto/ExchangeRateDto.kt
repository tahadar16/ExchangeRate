package com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto

data class ExchangeRateDto(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Map<String , Double>,
    val timestamp: Int
)