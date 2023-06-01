package com.tahadardev.exchangerate.feature.currency_exchange.data.repository

import androidx.compose.ui.platform.LocalContext
import com.tahadardev.exchangerate.common.Resource
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.WebApi
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto.ExchangeRateDto
import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.dto.toExchangeRate
import com.tahadardev.exchangerate.feature.currency_exchange.domain.model.ExchangeRates
import com.tahadardev.exchangerate.feature.currency_exchange.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: WebApi
) : CurrencyRepository {

    override suspend fun fetchCurrencies(): Flow<Resource<Map<String, String>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = api.fetchCurrencies()
                response.body()?.let {
                    return@flow emit(Resource.Success(it))
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

    override suspend fun fetchExchangeRates(): Flow<Resource<ExchangeRates>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = api.fetchExchangeRates()
                response.body()?.let {
                    return@flow emit(Resource.Success(it.toExchangeRate()))
                }
                emit(Resource.Error(message = "Unexpected error occurred"))
            } catch (error: HttpException) {
                emit(Resource.Error(message = error.message ?: "Something went wrong"))
            } catch (error: IOException) {
                emit(
                    Resource.Error("Couldn't connect to the server. Check your network")
                )
            }
        }
    }
}