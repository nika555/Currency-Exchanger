package com.example.currencyexchanger.di

import com.example.currencyexchanger.data.repositories.CurrencyAccountRepositoryImpl
import com.example.currencyexchanger.data.repositories.RatesRepositoryImpl
import com.example.currencyexchanger.domain.repositories.CurrencyAccountRepository
import com.example.currencyexchanger.domain.repositories.RatesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCurrencyAccountRepositoryImpl(
        repo: CurrencyAccountRepositoryImpl
    ): CurrencyAccountRepository

    @Binds
    @Singleton
    abstract fun bindRatesRepositoryImpl(
        repo: RatesRepositoryImpl
    ): RatesRepository
}