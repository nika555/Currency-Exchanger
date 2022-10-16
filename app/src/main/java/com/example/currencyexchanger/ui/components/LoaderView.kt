package com.example.currencyexchanger.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.currencyexchanger.ui.theme.Gray

@Composable
fun LoaderView() {
    Box(
        modifier = Modifier
            .background(Gray.copy(alpha = 0.7f))
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
@Preview
fun LoaderPreView() {
    LoaderView()
}
