package com.example.currencyexchanger.domain.models

data class RateModel(
    val base: String?,
    val rates: List<Rate>
) {
    data class Rate(
        val id: Int?,
        val currency: String?,
        val rate: Double?
    )
}
