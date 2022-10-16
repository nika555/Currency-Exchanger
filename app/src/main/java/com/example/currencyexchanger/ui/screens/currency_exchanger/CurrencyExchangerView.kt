package com.example.currencyexchanger.ui.screens.currency_exchanger

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.currencyexchanger.R
import com.example.currencyexchanger.ui.components.*
import com.example.currencyexchanger.ui.navigation.bottom_sheet.BottomSheetScreen
import com.example.currencyexchanger.ui.screens.currency_exchanger.components.balance.BalancesView
import com.example.currencyexchanger.ui.screens.currency_exchanger.components.SectionTitle
import com.example.currencyexchanger.ui.screens.currency_exchanger.components.currency_exchange.ExchangeView
import com.example.currencyexchanger.ui.theme.Green
import com.example.currencyexchanger.ui.theme.Red
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@Composable
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
fun CurrencyExchangerView(
    viewModel: CurrencyExchangerViewModel = hiltViewModel(),
    openSheet: (BottomSheetScreen) -> Unit,
) {
    val uiState = viewModel.currencyExchangerUiState.value
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val (focusRequester) = FocusRequester.createRefs()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchCurrency()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> viewModel.cancelRatePollerJob()
                Lifecycle.Event.ON_START -> viewModel.startRatesPollerJob()
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    fun chooseSell() {
        openSheet(
            BottomSheetScreen.RateChooserSheet(
                state = viewModel.currencyExchangerState,
                busyCurrencyId = uiState.receiveCurrencyState.value?.id,
                itemAction = {
                    uiState.sellCurrencyState.value = it
                    uiState.calculateSell()
                })
        )
    }

    fun chooseReceive() {
        openSheet(
            BottomSheetScreen.RateChooserSheet(
                state = viewModel.currencyExchangerState,
                busyCurrencyId = uiState.sellCurrencyState.value?.id,
                itemAction = {
                    uiState.receiveCurrencyState.value = it
                    uiState.calculateReceive()
                })
        )
    }

    fun currencyChange() {
        if (viewModel.isBalanceAvailable()) {
            viewModel.currencyExchange()

            val freeOperations = viewModel.freeOperations()
            var desc = context.getString(R.string.you_have_converted)
                .plus(" ")
                .plus(uiState.sellInputState.value)
                .plus(" ")
                .plus(uiState.sellCurrencyState.value?.currency)
                .plus(" ")
                .plus(context.getString(R.string.to))
                .plus(" ")
                .plus(uiState.receiveInputState.value)
                .plus(" ")
                .plus(uiState.receiveCurrencyState.value?.currency)
                .plus(".")

            if (freeOperations == 0) {
                desc = desc.plus(" ")
                    .plus(context.getString(R.string.commission_fee))
                    .plus(" ")
                    .plus(viewModel.calculateFee()).plus(" ")
                    .plus(uiState.sellCurrencyState.value?.currency)
                    .plus(".")
            }
            uiState.updateDialogState(
                messageType = MessageType.SUCCESS,
                title = context.getString(R.string.currency_exchange),
                desc = desc
            )
            viewModel.decreaseExchangeOperationCount()
            uiState.resetInputs()
        } else {
            uiState.updateDialogState(
                messageType = MessageType.ERROR,
                title = context.getString(R.string.incorrect_request),
                desc = context.getString(R.string.there_is_not_enough_money_in_the_account)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
            .verticalScroll(state = rememberScrollState(), true)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                })
            },
        horizontalAlignment = Alignment.Start,
    ) {

        TopNavigation(title = R.string.currency_converter)



        Column {
            BalancesView(balances = viewModel.currencyExchangerState.value.currencyAccounts)

            Column(modifier = Modifier.padding(top = 30.dp, start = 16.dp)) {

                SectionTitle(text = R.string.currency_exchange)

                ExchangeView(
                    modifier = Modifier.padding(top = 30.dp),
                    focusRequester = focusRequester,
                    currencyInputState = uiState.sellInputState,
                    chooserState = uiState.sellCurrencyState,
                    iconColor = Red,
                    iconRotate = 90f,
                    title = R.string.sell,
                    onValueChange = {
                        uiState.calculateSell()
                    },
                    chooserOnClick = {
                        chooseSell()
                    })

                ExchangeView(modifier = Modifier.padding(top = 20.dp),
                    focusRequester = focusRequester,
                    currencyInputState = uiState.receiveInputState,
                    chooserState = uiState.receiveCurrencyState,
                    iconColor = Green,
                    iconRotate = -90f,
                    title = R.string.receive,
                    onValueChange = {
                        uiState.calculateReceive()
                    },
                    chooserOnClick = {
                        chooseReceive()
                    })
            }
        }

        Spacer(modifier = Modifier.padding(top = 40.dp))

        ButtonComponent(
            modifier = Modifier
                .padding(start = 22.dp, end = 22.dp),
            title = R.string.app_name,
            enabled = uiState.isCurrencyExchangeAvailable(),
            onClick = {
                currencyChange()
            },
        )
    }

    if (uiState.openDialog.value)
        StatusDialog(
            openDialog = uiState.openDialog,
            message = uiState.dialogContentState.value
        )

    if (viewModel.currencyExchangerState.value.isLoading) LoaderView()
}

@ExperimentalComposeUiApi
@Composable
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@Preview
fun CurrencyExchangerPreView() {
    CurrencyExchangerView(openSheet = {})
}