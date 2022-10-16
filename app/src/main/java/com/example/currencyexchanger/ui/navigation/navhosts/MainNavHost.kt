package com.example.currencyexchanger.ui.navigation.navhosts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currencyexchanger.ui.navigation.MainScreen
import com.example.currencyexchanger.ui.navigation.bottom_sheet.BottomSheetScreen
import com.example.currencyexchanger.ui.screens.currency_exchanger.CurrencyExchangerView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalStdlibApi
@ExperimentalComposeUiApi
@Composable
fun MainNavHost(
    padding: PaddingValues,
    rootNavController: NavHostController,
    openSheet: (BottomSheetScreen) -> Unit,
    closeSheet: () -> Unit,
) {
    NavHost(
        rootNavController,
        startDestination = MainScreen.CurrencyExchangerScreen.route,
        route = "Main"
    ) {
        composable(
            route = MainScreen.CurrencyExchangerScreen.route,
        ) {
            CurrencyExchangerView(
                openSheet = openSheet
            )
        }
    }
}
