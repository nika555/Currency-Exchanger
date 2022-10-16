package com.example.currencyexchanger.ui.screens.currency_exchanger

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.currencyexchanger.common.BaseViewModel
import com.example.currencyexchanger.domain.usecases.CurrencyAccountUseCase
import com.example.currencyexchanger.domain.usecases.LoadRatesUseCase
import com.example.currencyexchanger.extensions.roundToDouble
import com.example.currencyexchanger.ui.screens.currency_exchanger.states.CurrencyExchangerState
import com.example.currencyexchanger.ui.screens.currency_exchanger.states.CurrencyExchangerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyExchangerViewModel @Inject constructor(
    private val currencyAccountUseCase: CurrencyAccountUseCase,
    private val loadRatesUseCase: LoadRatesUseCase
) : BaseViewModel() {

    companion object {
        const val FEE = 0.7
        const val FREE_OPERATIONS = 5
        const val RATES_PERIODIC = 5000L
    }

    private val _currencyExchangerState: MutableState<CurrencyExchangerState> =
        mutableStateOf(CurrencyExchangerState(freeOperations = FREE_OPERATIONS))
    val currencyExchangerState: State<CurrencyExchangerState> = _currencyExchangerState

    private val _currencyExchangerUiState: MutableState<CurrencyExchangerUiState> =
        mutableStateOf(CurrencyExchangerUiState())
    val currencyExchangerUiState: State<CurrencyExchangerUiState> = _currencyExchangerUiState

    var ratePollerJob: Job? = null

    fun fetchCurrency() {
        handleResponse(apiCall = { currencyAccountUseCase.invoke() }, onLoading = {
            _currencyExchangerState.value =
                _currencyExchangerState.value.copy(isLoading = it, errorMessage = null)
        }, onSuccess = {
            _currencyExchangerState.value = _currencyExchangerState.value.copy(
                currencyAccounts = it, errorMessage = null
            )
        }, onError = {
            _currencyExchangerState.value = _currencyExchangerState.value.copy(
                currencyAccounts = null, errorMessage = it
            )
        })
    }

    private fun fetchRates(isFirst: Boolean) {
        handleResponse(apiCall = { loadRatesUseCase.invoke() }, onLoading = {
            if (isFirst) _currencyExchangerState.value =
                _currencyExchangerState.value.copy(isLoading = it, errorMessage = null)
        }, onSuccess = { data ->
            _currencyExchangerState.value = _currencyExchangerState.value.copy(
                rates = data.rates, baseRate = data.base, errorMessage = null
            )
            _currencyExchangerUiState.value.setSellCurrencyType(data.rates.firstOrNull {
                it.currency == data.base
            })
            currencyExchangerUiState.value.updateSelectedCurrencies(sell = data.rates.firstOrNull {
                it.id == currencyExchangerUiState.value.sellCurrencyState.value?.id
            }, receive = data.rates.firstOrNull {
                it.id == currencyExchangerUiState.value.receiveCurrencyState.value?.id
            })
        }, onError = {
            if (isFirst) _currencyExchangerState.value = _currencyExchangerState.value.copy(
                currencyAccounts = null, errorMessage = it
            )
        })
    }

    private fun ratePollerJob(): Job {
        var isFirst = true
        return viewModelScope.launch {
            while (isActive) {
                fetchRates(isFirst)
                isFirst = false
                delay(RATES_PERIODIC)
            }
        }
    }

    fun cancelRatePollerJob() {
        ratePollerJob?.cancel()
    }

    fun startRatesPollerJob() {
        ratePollerJob = ratePollerJob()
    }

    fun isBalanceAvailable(): Boolean {
        val sell = currencyExchangerUiState.value.sellCurrencyState.value
        val balance = currencyExchangerState.value.currencyAccounts?.firstOrNull {
            it.accountCurrency == sell?.currency
        }
        balance?.let {
            return it.accountBalance >= currencyExchangerUiState.value.sellInputState.value.toDouble() + calculateFee()
        }
        return false
    }

    fun currencyExchange() {
        //Use Some Api Request for Currency Exchange. Now it will be fake
        decreaseSellBalance()
        increaseReceiveBalance()
    }

    fun freeOperations() = _currencyExchangerState.value.freeOperations

    fun decreaseExchangeOperationCount() {
        val operations = _currencyExchangerState.value.freeOperations
        if (operations > 0) _currencyExchangerState.value.freeOperations = operations - 1
    }

    fun calculateFee(): Double {
        val operations = _currencyExchangerState.value.freeOperations
        return if (operations == 0) {
            val sellValue = currencyExchangerUiState.value.sellInputState.value.toDouble()
            ((sellValue * FEE) / 100).roundToDouble()
        } else return 0.0
    }

    private fun decreaseSellBalance() {
        val sellId = currencyExchangerUiState.value.sellCurrencyState.value?.currency
        val sellValue = currencyExchangerUiState.value.sellInputState.value.toDouble()
        val index = _currencyExchangerState.value.currencyAccounts?.indexOfFirst {
            it.accountCurrency == sellId
        }
        val oldBalances = _currencyExchangerState.value.currencyAccounts

        index?.let { ac ->
            _currencyExchangerState.value.currencyAccounts = emptyList()
            _currencyExchangerState.value =
                _currencyExchangerState.value.copy(currencyAccounts = oldBalances!!.toMutableList()
                    .apply {
                        get(ac).accountBalance =
                            (get(ac).accountBalance - sellValue - calculateFee()).roundToDouble()
                    })
        }
    }

    private fun increaseReceiveBalance() {
        val receiveId = currencyExchangerUiState.value.receiveCurrencyState.value?.currency
        val receiveValue = currencyExchangerUiState.value.receiveInputState.value.toDouble()
        val index = _currencyExchangerState.value.currencyAccounts?.indexOfFirst {
            it.accountCurrency == receiveId
        }
        val oldBalances = _currencyExchangerState.value.currencyAccounts

        index?.let { ac ->
            _currencyExchangerState.value.currencyAccounts = emptyList()
            _currencyExchangerState.value =
                _currencyExchangerState.value.copy(currencyAccounts = oldBalances!!.toMutableList()
                    .apply {
                        get(ac).accountBalance =
                            (get(ac).accountBalance + receiveValue).roundToDouble()
                    })
        }

    }
}