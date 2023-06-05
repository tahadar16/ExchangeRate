package com.tahadardev.exchangerate.feature.currency_exchange.data.repository

import com.tahadardev.exchangerate.common.Resource
import com.tahadardev.exchangerate.feature.currency_exchange.data.local.CurrencyDao
import com.tahadardev.exchangerate.feature.currency_exchange.data.local.CurrencyRateDao
import com.tahadardev.exchangerate.feature.currency_exchange.data.local.entity.CurrencyEntity
import com.tahadardev.exchangerate.feature.currency_exchange.data.local.entity.toCurrency
import com.tahadardev.exchangerate.feature.currency_exchange.data.local.entity.toCurrencyRate
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.WebApi
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto.toCurrencyRateEntity
import com.tahadardev.exchangerate.feature.currency_exchange.domain.model.Currency
import com.tahadardev.exchangerate.feature.currency_exchange.domain.model.CurrencyRate
import com.tahadardev.exchangerate.feature.currency_exchange.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyRateDao: CurrencyRateDao,
    private val api: WebApi
) : CurrencyRepository {
    override suspend fun fetchCurrencies(): Flow<Resource<List<Currency>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val currencyRecords = currencyDao.getCurrencies()
                val currencies = currencyRecords.map { it.toCurrency() }

                //TODO check the timestamp thing
                if (currencies.isNotEmpty()) {
                    return@flow emit(Resource.Success(currencies))
                }

                val response = api.fetchCurrencies()
                response.body()?.let { currencyMap ->
                    currencyDao.removeCurrencies(currencyRecords)
                    currencyDao.insertCurrencies(currencyMap.map { (key, value) ->
                        CurrencyEntity(
                            key,
                            value
                        )
                    })
                    return@flow emit(
                        Resource.Success(
                            currencyDao.getCurrencies().map { it.toCurrency() })
                    )
                }
                emit(Resource.Error(message = "Unexpected error occurred"))
            } catch (error: HttpException) {
                emit(Resource.Error(message = error.localizedMessage ?: "Something went wrong"))
            } catch (error: IOException) {
                emit(
                    Resource.Error("Couldn't connect to the server. Check your network")
                )
            }
        }
    }

    override suspend fun fetchExchangeRates(): Flow<Resource<List<CurrencyRate>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val prevExchangeRateRecords = currencyRateDao.getCurrencyRates()
                val prevExchangeRates = prevExchangeRateRecords.map { it.toCurrencyRate() }

                //TODO add check for timestamp
                if (prevExchangeRates.isNotEmpty()) {
                    return@flow emit(Resource.Success(prevExchangeRates))
                }

                val response = api.fetchExchangeRates()
                response.body()?.let { exchangeRateDto ->
                    currencyRateDao.removeCurrencyRates(prevExchangeRateRecords)
                    currencyRateDao.insertCurrencyRates(exchangeRateDto.rates.map { it.toCurrencyRateEntity() })
                    return@flow emit(
                        Resource.Success(
                            currencyRateDao.getCurrencyRates().map { it.toCurrencyRate() })
                    )
                }
                emit(
                    Resource.Error(message = "Unexpected error occurred")
                )
            } catch (error: HttpException) {
                emit(
                    Resource.Error(message = error.message ?: "Something went wrong")
                )
            } catch (error: IOException) {
                emit(
                    Resource.Error("Couldn't connect to the server. Check your network")
                )
            }
        }
    }
}