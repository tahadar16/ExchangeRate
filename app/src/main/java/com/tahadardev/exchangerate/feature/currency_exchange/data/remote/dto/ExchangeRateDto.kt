package com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto

import com.tahadardev.exchangerate.feature.currency_exchange.domain.model.ExchangeRates

data class ExchangeRateDto(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: MutableMap<String , Double>,
    val timestamp: Int
)

fun ExchangeRateDto.toExchangeRate() : ExchangeRates {
    return ExchangeRates(rates, timestamp)
}