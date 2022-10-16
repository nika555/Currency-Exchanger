package com.example.currencyexchanger.ui.screens.currency_exchanger.components.balance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyexchanger.R
import com.example.currencyexchanger.domain.models.CurrencyAccount
import com.example.currencyexchanger.ui.screens.currency_exchanger.components.SectionTitle

@Composable
fun BalancesView(balances: List<CurrencyAccount>?) {
    Column(modifier = Modifier.padding(top = 30.dp, start = 16.dp)) {

        SectionTitle(text = R.string.my_balances)

        LazyRow(
            modifier = Modifier
                .height(100.dp)
                .padding(top = 40.dp)
        ) {
            items(balances ?: emptyList()) { item ->
                BalanceItemView(
                    balance = item.accountBalance,
                    currency = item.accountCurrency
                )
            }
        }
    }
}


@Composable
@Preview
fun BalancesPreView() {
    BalancesView(balances = emptyList())
}