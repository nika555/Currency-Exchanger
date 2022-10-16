package com.example.currencyexchanger.domain.repositories

import com.example.currencyexchanger.common.Resource
import com.example.currencyexchanger.domain.models.CurrencyAccount
import kotlinx.coroutines.flow.Flow

interface CurrencyAccountRepository {

    suspend fun getAccountCurrency(): Flow<Resource<List<CurrencyAccount>>>

}