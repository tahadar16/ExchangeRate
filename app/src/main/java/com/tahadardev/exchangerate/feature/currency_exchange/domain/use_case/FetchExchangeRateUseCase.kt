package com.tahadardev.exchangerate.feature.currency_exchange.domain.use_case

import com.tahadardev.exchangerate.common.Resource
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto.ExchangeRateDto
import com.tahadardev.exchangerate.feature.currency_exchange.domain.model.ExchangeRates
import com.tahadardev.exchangerate.feature.currency_exchange.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchExchangeRateUseCase @Inject constructor(
    private val repo : CurrencyRepository
){
    suspend operator fun invoke() : Flow<Resource<ExchangeRates>> {
        return repo.fetchExchangeRates()
    }
}