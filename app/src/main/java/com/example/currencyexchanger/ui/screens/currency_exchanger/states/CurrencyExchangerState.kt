package com.example.currencyexchanger.ui.screens.currency_exchanger.states

import com.example.currencyexchanger.domain.models.CurrencyAccount
import com.example.currencyexchanger.domain.models.RateModel

data class CurrencyExchangerState(
    val isLoading: Boolean = false,
    var currencyAccounts: List<CurrencyAccount>? = null,
    var rates: List<RateModel.Rate>? = null,
    var baseRate: String? = null,
    var freeOperations: Int = 0,
    var errorMessage: String? = null,
)