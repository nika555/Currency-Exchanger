package com.example.currencyexchanger.ui.screens.currency_exchanger.components.balance

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.ui.theme.Black

@Composable
fun BalanceItemView(balance: Double, currency: String) {
    Text(
        text = balance.toString().plus(" ").plus(currency),
        modifier = Modifier.padding(end = 20.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Black
    )
}

@Composable
@Preview
fun BalanceItemPreView() {
    BalanceItemView(balance = 0.0, currency = "")
}
