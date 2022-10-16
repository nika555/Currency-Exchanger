package com.example.currencyexchanger.ui.screens.currency_exchanger.components.currency_exchange

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.R
import com.example.currencyexchanger.domain.models.RateModel
import com.example.currencyexchanger.ui.components.ChooserComponent
import com.example.currencyexchanger.ui.components.TextFieldComponent
import com.example.currencyexchanger.ui.theme.Black
import com.example.currencyexchanger.ui.theme.Gray

@ExperimentalComposeUiApi
@Composable
fun ExchangeView(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    currencyInputState: MutableState<String>,
    chooserState: MutableState<RateModel.Rate?>,
    iconColor: Color,
    iconRotate: Float,
    @StringRes
    title: Int,
    onValueChange: ((String) -> Unit) = {},
    chooserOnClick: () -> Unit,

    ) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
                .background(iconColor)
                .align(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow),
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(30.dp)
                    .rotate(iconRotate),
                contentDescription = "Profile placeholder"
            )
        }

        Spacer(modifier = Modifier.width(18.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = title),
                    modifier = Modifier.align(Alignment.CenterVertically).width(60.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Black
                )

                TextFieldComponent(
                    enabled = chooserState.value != null,
                    value = currencyInputState.value,
                    onValueChange = {
                        if (it.isEmpty() || it.toDoubleOrNull() != null) {
                            currencyInputState.value = it
                            onValueChange.invoke(it)
                        }
                    },
                    modifier = Modifier.focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    placeholder = "0.00",
                    trailingIcon = {
                        ChooserComponent(
                            title = chooserState.value?.currency ?: "",
                            placeholder = R.string.choose,
                            modifier = Modifier,
                            oncClick = chooserOnClick
                        )
                    }, onClick = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.please_select_currency),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }

            Spacer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Gray)
            )
        }
    }

}


@Composable
@Preview
fun CurrencyExchangePreView() {

}