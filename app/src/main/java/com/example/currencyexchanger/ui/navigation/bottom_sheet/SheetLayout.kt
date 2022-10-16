package com.example.currencyexchanger.ui.navigation.bottom_sheet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.currencyexchanger.ui.screens.currency_exchanger.components.currency_exchange.RateChooserSheet


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SheetLayout(currentScreen: BottomSheetScreen, onCloseBottomSheet: () -> Unit) {
    BottomSheetWithCloseDialog(onCloseBottomSheet) {
        when (currentScreen) {
            is BottomSheetScreen.RateChooserSheet -> RateChooserSheet(
                state = currentScreen.state,
                busyCurrencyId = currentScreen.busyCurrencyId,
                itemAction = currentScreen.itemAction,
                onCloseBottomSheet = onCloseBottomSheet
            )
            else -> {}
        }
    }
}