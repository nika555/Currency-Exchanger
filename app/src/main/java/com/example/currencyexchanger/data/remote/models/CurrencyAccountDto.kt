package com.example.currencyexchanger.data.remote.models

import com.example.currencyexchanger.domain.models.CurrencyAccount
import com.squareup.moshi.Json

data class CurrencyAccountDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "accountCurrency")
    val accountCurrency: String,
    @Json(name = "accountBalance")
    val accountBalance: Double,
) {
    fun toCurrencyAccount() = CurrencyAccount(
        id = id,
        accountCurrency = accountCurrency,
        accountBalance = accountBalance,
    )

}