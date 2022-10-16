package com.example.currencyexchanger.domain.models

data class CurrencyAccount(
    val id: Int,
    val accountCurrency: String,
    var accountBalance: Double,
)