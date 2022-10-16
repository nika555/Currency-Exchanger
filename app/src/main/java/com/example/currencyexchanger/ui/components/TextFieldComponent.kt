package com.example.currencyexchanger.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.ui.theme.Black
import com.example.currencyexchanger.ui.theme.Gray


@ExperimentalComposeUiApi
@Composable
fun TextFieldComponent(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: ((String) -> Unit) = {},
    focusRequester: FocusRequester = FocusRequester.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textStyle: TextStyle = TextStyle(
        color = Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    placeholder: String = "",
    readOnly: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        backgroundColor = Color.White,
        disabledIndicatorColor = Color.White,
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {

    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    val textFieldValue = textFieldValueState.copy(text = value)

    TextField(
        enabled = enabled,
        readOnly = readOnly,
        value = textFieldValue,
        onValueChange = {
            textFieldValueState = it
            if (value != it.text) {
                onValueChange(it.text)
            }
        },
        modifier = Modifier
            .focusRequester(focusRequester)
            .then(modifier)
            .clickable {
                onClick?.invoke()
            },
        singleLine = true,
        textStyle = textStyle,
        placeholder = {
            Text(
                placeholder,
                maxLines = 1,
                color = Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = 1,
        interactionSource = remember { MutableInteractionSource() },
        colors = colors
    )
}