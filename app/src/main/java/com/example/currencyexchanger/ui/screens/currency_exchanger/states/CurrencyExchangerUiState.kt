package com.example.currencyexchanger.ui.screens.currency_exchanger.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.currencyexchanger.domain.models.RateModel
import com.example.currencyexchanger.extensions.roundToDouble
import com.example.currencyexchanger.ui.components.Message
import com.example.currencyexchanger.ui.components.MessageType

class CurrencyExchangerUiState {
    val sellInputState = mutableStateOf("")
    val receiveInputState = mutableStateOf("")
    var sellCurrencyState: MutableState<RateModel.Rate?> = mutableStateOf(null)
    var receiveCurrencyState: MutableState<RateModel.Rate?> = mutableStateOf(null)

    val dialogContentState = mutableStateOf(Message())
    val openDialog = mutableStateOf(false)

    fun setSellCurrencyType(rate: RateModel.Rate?) {
        if (sellCurrencyState.value == null) rate?.let {
            sellCurrencyState.value = it
        }
    }

    fun calculateSell() {
        var sellValue = sellInputState.value.toDoubleOrNull()
        if (sellValue != null) {
            sellValue /= sellCurrencyState.value!!.rate!!
            receiveCurrencyState.value?.rate?.let {
                receiveInputState.value =
                    ((sellValue * it).toBigDecimal().toDouble().roundToDouble().toString())
            }
        } else receiveInputState.value = ""
    }

    fun calculateReceive() {
        var receiveValue = receiveInputState.value.toDoubleOrNull()
        if (receiveValue != null) {
            receiveValue /= receiveCurrencyState.value!!.rate!!
            sellCurrencyState.value?.rate?.let {
                sellInputState.value =
                    (receiveValue / it).toBigDecimal().toDouble().roundToDouble().toString()
            }
        } else sellInputState.value = ""
    }

    fun isCurrencyExchangeAvailable() =
        sellInputState.value.isNotEmpty() && receiveInputState.value.isNotEmpty()

    fun updateDialogState(messageType: MessageType, title: String, desc: String) {
        dialogContentState.value =
            dialogContentState.value.copy(messageType = messageType, title = title, desc = desc)
        openDialog.value = true
    }

    fun resetInputs() {
        sellInputState.value = ""
        receiveInputState.value = ""
    }

    fun updateSelectedCurrencies(sell: RateModel.Rate?, receive: RateModel.Rate?) {
        sellCurrencyState.value = sell
        receiveCurrencyState.value = receive
    }
}

