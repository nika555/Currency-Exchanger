package com.example.currencyexchanger.ui.screens.currency_exchanger.components.currency_exchange

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.domain.models.RateModel
import com.example.currencyexchanger.ui.theme.DividerColor
import com.example.currencyexchanger.ui.theme.MainGradientStart

@Composable
fun RateChooserItem(
    itemAction: (rate: RateModel.Rate) -> Unit,
    rate:RateModel.Rate?,
    onCloseBottomSheet: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clickable(
                onClick = {
                    rate?.let { itemAction.invoke(it) }
                    onCloseBottomSheet.invoke()
                },
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MainGradientStart, fontWeight = FontWeight.Bold, fontSize = 16.sp
                    )
                ) {
                    append("${rate?.currency} -")
                }
                append(" ${rate?.rate}")
            },
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black
        )

        Divider(
            color = DividerColor,
            modifier = Modifier
                .height(1.dp)
                .background(DividerColor)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            thickness = 1.dp,
        )
    }
}

@Composable
@Preview
fun RateChooserItemPreView() {
    RateChooserItem(itemAction = { },
        rate = null,
        onCloseBottomSheet = {})
}