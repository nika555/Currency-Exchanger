package com.example.currencyexchanger.data.repositories

import com.example.currencyexchanger.common.Resource
import com.example.currencyexchanger.common.ResponseHandler
import com.example.currencyexchanger.common.mapListSuccess
import com.example.currencyexchanger.data.remote.services.CurrencyAccountService
import com.example.currencyexchanger.domain.models.CurrencyAccount
import com.example.currencyexchanger.domain.repositories.CurrencyAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyAccountRepositoryImpl @Inject constructor(
    private val api: CurrencyAccountService,
    private val handler: ResponseHandler
) : CurrencyAccountRepository {

    override suspend fun getAccountCurrency(): Flow<Resource<List<CurrencyAccount>>> {
        return handler.safeApiCall {
            api.currencyAccounts()
        }.map { resource ->
            resource.mapListSuccess { dto ->
                dto.toCurrencyAccount()
            }
        }
    }
}