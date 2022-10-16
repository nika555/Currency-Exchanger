package com.example.currencyexchanger.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyexchanger.R
import com.example.currencyexchanger.ui.theme.Black

@Composable
fun ChooserComponent(
    title:String,
    @StringRes
    placeholder: Int,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    modifier: Modifier = Modifier,
    oncClick: () -> Unit
) {
    OutlinedButton(
        onClick = {
            oncClick.invoke()
        },
        colors = colors,
        border = border,
        modifier = Modifier
            .height(56.dp)
            .then(modifier),
    ) {
        Text(
            text = title.ifEmpty { stringResource(id = placeholder) },
            fontSize = 18.sp,
            color = Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(end = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            modifier = Modifier
                .rotate(90f),
            painter = painterResource(id = R.drawable.ic_navigation_arrow_right),
            contentDescription = "ic_navigation_arrow_right"
        )
    }
}

@Composable
@Preview
fun ChooserComponentPreView() {
    ChooserComponent(
        title = "",
        placeholder = R.string.choose,
        oncClick = {}
    )
}