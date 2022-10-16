package com.example.currencyexchanger.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyexchanger.R
import com.example.currencyexchanger.ui.theme.MainGradient

@Composable
fun TopNavigation(
    modifier: Modifier = Modifier,
    @StringRes
    title: Int
) {
    Column(
        modifier = Modifier
            .background(MainGradient)
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {

            Title(
                text = title, modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            )
        }
    }
}


@Preview
@Composable
fun TopNavigationPreview() {
    TopNavigation(
        title = R.string.app_name
    )
}