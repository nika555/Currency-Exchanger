package com.example.currencyexchanger.data.remote.services

import com.example.currencyexchanger.utils.ApiUtil
import com.example.currencyexchanger.data.remote.models.CurrencyAccountDto
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyAccountService {

    @GET(ApiUtil.ACCOUNT_CURRENCY)
    suspend fun currencyAccounts(): Response<List<CurrencyAccountDto>>
}