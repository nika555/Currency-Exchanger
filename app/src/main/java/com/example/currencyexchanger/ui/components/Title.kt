package com.example.currencyexchanger.ui.components

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
fun Title(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 18.sp,
    color: Color = Color.White
) {
    Text(
        text = stringResource(id = text),
        modifier = modifier,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color
    )
}

@Preview
@Composable
fun TitlePreview() {
    Title(
        text = R.string.app_name
    )
}