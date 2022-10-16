package com.example.currencyexchanger.ui.screens.currency_exchanger.components

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.R

@Composable
fun SectionTitle(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.Gray
) {
    Text(
        text = stringResource(id = text).uppercase(),
        modifier = modifier,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
    )
}

@Preview
@Composable
fun SectionTitlePreview() {
    SectionTitle(
        text = R.string.app_name
    )
}