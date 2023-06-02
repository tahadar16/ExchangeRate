package com.tahadardev.exchangerate.feature.currency_exchange.domain.util

object Utils {

    fun getConversionRate(searchValue: String, exchangeRate: Double): String {

        val userSearchValue : Double = searchValue.toDouble()
        val convertedRate :Double = userSearchValue * exchangeRate
        
        return "%.2f".format(convertedRate)
    }
}