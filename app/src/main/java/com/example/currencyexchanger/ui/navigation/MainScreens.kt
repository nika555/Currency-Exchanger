package com.example.currencyexchanger.ui.navigation

import androidx.annotation.StringRes
import com.example.currencyexchanger.R


sealed class MainScreen(
    @StringRes
    val nameId: Int,
    val route: String,
) {

    object CurrencyExchangerScreen : MainScreen(nameId = R.string.app_name, route = "CurrencyExchangerScreen")

}