package com.example.currencyexchanger.ui.navigation.bottom_sheet

import androidx.compose.runtime.State
import com.example.currencyexchanger.domain.models.RateModel
import com.example.currencyexchanger.ui.screens.currency_exchanger.states.CurrencyExchangerState


sealed class BottomSheetScreen {

    class RateChooserSheet(
        val state: State<CurrencyExchangerState>,
        val busyCurrencyId: Int?,
        val itemAction: (rate: RateModel.Rate) -> Unit,
    ) : BottomSheetScreen()


}