package com.example.currencyexchanger.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.ui.theme.DisableColor
import com.example.currencyexchanger.ui.theme.MainGradientStart


@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    @StringRes
    title: Int,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(27.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        disabledBackgroundColor = DisableColor,
        disabledContentColor = Color.White,
        backgroundColor = MainGradientStart,
        contentColor = Color.White
    ),
    titleFontSize: TextUnit = 16.sp,
    onClick: () -> Unit,
) {
    Button(
        shape = shape,
        enabled = enabled,
        onClick = {
            onClick.invoke()
        },
        colors = colors,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .composed { modifier }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(id = title),
                fontSize = titleFontSize,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
        }
    }
}
