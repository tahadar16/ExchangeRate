package com.tahadardev.exchangerate.feature.currency_exchange.data.repository

import com.tahadardev.exchangerate.common.Resource
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.WebApi
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto.ExchangeRateDto
import com.tahadardev.exchangerate.feature.currency_exchange.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: WebApi
) : CurrencyRepository {

    override suspend fun fetchCurrencies(): Flow<Resource<Map<String, String>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchExchangeRates(): Flow<Resource<ExchangeRateDto>> {
        TODO("Not yet implemented")
    }

}