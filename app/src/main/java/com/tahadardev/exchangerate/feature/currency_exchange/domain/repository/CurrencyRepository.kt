package com.tahadardev.exchangerate.feature.currency_exchange.domain.repository

import com.tahadardev.exchangerate.common.Resource
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto.ExchangeRateDto
import com.tahadardev.exchangerate.feature.currency_exchange.domain.model.ExchangeRates
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun fetchCurrencies() : Flow<Resource<Map<String, String>>>

    suspend fun fetchExchangeRates() : Flow<Resource<ExchangeRates>>
}