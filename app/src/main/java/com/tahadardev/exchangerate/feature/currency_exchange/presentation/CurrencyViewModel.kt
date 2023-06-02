package com.tahadardev.exchangerate.feature.currency_exchange.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahadardev.exchangerate.common.Resource
import com.tahadardev.exchangerate.feature.currency_exchange.domain.use_case.FetchCurrenciesUseCase
import com.tahadardev.exchangerate.feature.currency_exchange.domain.use_case.FetchExchangeRateUseCase
import com.tahadardev.exchangerate.feature.currency_exchange.domain.use_case.UpdateExchangeRatesUseCase
import com.tahadardev.exchangerate.feature.currency_exchange.presentation.screens.stateModels.CurrencyExchangeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val fetchCurrenciesUseCase: FetchCurrenciesUseCase,
    private val fetchExchangeRateUseCase: FetchExchangeRateUseCase,
    private val updateExchangeRatesUseCase: UpdateExchangeRatesUseCase
) : ViewModel() {
    private val state = mutableStateOf(CurrencyExchangeState())
    var currencyExchangeState = state

    init {
        fetchCurrencies()
        fetchExchangeRates()
    }

    private fun fetchCurrencies() {
        viewModelScope.launch {
            fetchCurrenciesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> state.value = state.value.copy(
                        isLoading = true
                    )

                    is Resource.Success -> state.value = state.value.copy(
                        isLoading = false,
                        currencyList = result.data ?: hashMapOf()
                    )

                    is Resource.Error -> state.value = state.value.copy(
                        isLoading = false,
                        errorMsg = result.message ?: "Something went wrong"
                    )
                }
            }
        }
    }

    private fun fetchExchangeRates() {
        viewModelScope.launch {
            fetchExchangeRateUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> state.value = state.value.copy(
                        isLoading = true
                    )

                    is Resource.Success -> state.value = state.value.copy(
                        isLoading = false,
                        exchangeRates = result.data?.rates ?: hashMapOf()
                    )

                    is Resource.Error -> state.value = state.value.copy(
                        isLoading = false,
                        errorMsg = result.message ?: "Something went wrong"
                    )
                }
            }
        }
    }

    fun updateExchangeRates(selectedCurrency : String) {
        viewModelScope.launch {
            updateExchangeRatesUseCase(
                currencyExchangeState.value.exchangeRates,
                selectedCurrency).collect { result ->
                when (result) {
                    is Resource.Loading -> state.value = state.value.copy(
                        isLoading = true
                    )

                    is Resource.Success -> state.value = state.value.copy(
                        isLoading = false,
                        selectedCurrency = selectedCurrency,
                        exchangeRates = result.data ?: hashMapOf()
                    )

                    is Resource.Error -> state.value = state.value.copy(
                        isLoading = false,
                        errorMsg = result.message ?: "Unable to convert the exchange rates"
                    )
                }
            }
        }
    }
}