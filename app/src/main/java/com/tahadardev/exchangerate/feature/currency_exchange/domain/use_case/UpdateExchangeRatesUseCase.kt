package com.tahadardev.exchangerate.feature.currency_exchange.domain.use_case

import com.tahadardev.exchangerate.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateExchangeRatesUseCase @Inject constructor() {
    operator fun invoke(
        exchangeRates: MutableMap<String, Double>,
        selectedCurrency: String
    ): Flow<Resource<MutableMap<String, Double>>> {
        return flow {
            try {
                /*
                 *  x = 2y;
                 *  x = 3z;
                 *  y = 3z / 2
                 */
                emit(Resource.Loading())
                val selectedCurrencyRate = exchangeRates[selectedCurrency] ?: 1.00
                exchangeRates.replaceAll { _, value -> value / selectedCurrencyRate }
                emit(Resource.Success(exchangeRates))
            } catch (e: Exception) {
                emit(Resource.Error(message = "Unable to convert the exchange rates"))
            }
        }
    }
}