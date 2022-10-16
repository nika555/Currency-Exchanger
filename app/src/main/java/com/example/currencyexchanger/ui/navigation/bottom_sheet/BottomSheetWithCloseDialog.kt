package com.example.currencyexchanger.ui.navigation.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currencyexchanger.ui.theme.Vanilla
import  com.example.currencyexchanger.R

@Composable
fun BottomSheetWithCloseDialog(
    onClosePressed: () -> Unit,
    modifier: Modifier = Modifier,
    closeButtonColor: Color = Color.Gray,
    content: @Composable () -> Unit
) {
    Box(modifier.fillMaxWidth()) {
        content()
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .background(Vanilla)
                .width(100.dp)
                .height(5.dp)
                .align(Alignment.TopCenter)
        )

        IconButton(
            onClick = onClosePressed,
            modifier = Modifier
                .padding(16.dp)
                .size(29.dp)

        ) {
            Icon(
                painterResource(id = R.drawable.ic_navigation_arrow),
                tint = closeButtonColor,
                contentDescription = null
            )
        }
    }
}
