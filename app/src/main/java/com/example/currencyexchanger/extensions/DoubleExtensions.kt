package com.example.currencyexchanger.extensions

import kotlin.math.roundToInt

fun Double.roundToDouble(): Double {
    return (this * 10000.0).roundToInt() / 10000.0
}