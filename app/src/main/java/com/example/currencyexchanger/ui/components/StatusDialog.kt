package com.example.currencyexchanger.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.currencyexchanger.R
import com.example.currencyexchanger.ui.theme.Black
import com.example.currencyexchanger.ui.theme.DisableColor

enum class MessageType {
    ERROR, SUCCESS,
}

data class Message(
    val messageType: MessageType = MessageType.SUCCESS,
    val title: String = "",
    val desc: String = ""
)

@ExperimentalComposeUiApi
@Composable
fun StatusDialog(
    openDialog: MutableState<Boolean>,
    message: Message
) {

    AlertDialog(
        backgroundColor = Color.White,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(4.dp),
        properties = DialogProperties(
            dismissOnBackPress = true, dismissOnClickOutside = true, usePlatformDefaultWidth = false
        ),
        onDismissRequest = {
            openDialog.value = false
        },
        buttons = {
            Box {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        message.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.9.sp,
                        color = if (message.messageType == MessageType.ERROR) Color.Red else Color.Black
                    )

                    Spacer(modifier = Modifier.height(23.dp))

                    Divider(color = DisableColor, thickness = 0.5.dp)

                    Spacer(modifier = Modifier.height(23.dp))

                    Text(
                        message.desc,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Black,
                        letterSpacing = (-0.32).sp,
                        modifier = Modifier.padding(start = 74.dp, end = 74.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(23.dp))

                    Divider(color = DisableColor, thickness = 0.5.dp)

                    Spacer(modifier = Modifier.height(17.dp))

                    Button(
                        shape = RoundedCornerShape(50.dp),
                        onClick = {
                            openDialog.value = false
                        },
                        modifier = Modifier
                            .height(42.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .width(155.dp)
                    ) {
                        Text(
                            stringResource(id = R.string.done),
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(17.dp))
                }
            }
        },
    )
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun StatusDialogPreView() {
    StatusDialog(openDialog = remember {
        mutableStateOf(true)
    }, message = Message())
}

