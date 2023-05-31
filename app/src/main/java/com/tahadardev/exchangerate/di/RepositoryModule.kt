package com.tahadardev.exchangerate.di

import com.tahadardev.exchangerate.feature.currency_exchange.data.remote.WebApi
import com.tahadardev.exchangerate.feature.currency_exchange.data.repository.CurrencyRepositoryImpl
import com.tahadardev.exchangerate.feature.currency_exchange.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyRepo(api: WebApi) : CurrencyRepository {
        return CurrencyRepositoryImpl(api)
    }
}
