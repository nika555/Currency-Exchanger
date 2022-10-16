package com.example.currencyexchanger.ui.screens.currency_exchanger.components.currency_exchange

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.R
import com.example.currencyexchanger.domain.models.RateModel
import com.example.currencyexchanger.ui.components.TextFieldComponent
import com.example.currencyexchanger.ui.screens.currency_exchanger.states.CurrencyExchangerState
import com.example.currencyexchanger.ui.theme.Black

@ExperimentalComposeUiApi
@Composable
fun RateChooserSheet(
    state: State<CurrencyExchangerState>,
    busyCurrencyId:Int?,
    itemAction: (rate: RateModel.Rate) -> Unit,
    onCloseBottomSheet: () -> Unit
) {

    val searchState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RectangleShape)
            .padding(top = 16.dp)
            .fillMaxHeight(0.95f)

    ) {
        Text(
            text = stringResource(id = R.string.rates),
            Modifier.padding(start = 16.dp, top = 60.dp),
            color = Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        TextFieldComponent(
            value = searchState.value,
            onValueChange = {
                searchState.value = it
            },
            placeholder = stringResource(id = R.string.search_rate),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Black,
                unfocusedIndicatorColor = Black,
                backgroundColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
        )

        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(15.dp))
            }

            state.value.rates?.let { it ->
                items(it.toList().filter {
                    ((it.currency ?: "").lowercase()).contains(searchState.value.lowercase()) && busyCurrencyId != it.id
                }) { item ->
                    RateChooserItem(
                        itemAction = itemAction,
                        rate = item,
                        onCloseBottomSheet = onCloseBottomSheet,
                    )
                }
            }
        }
    }
}

