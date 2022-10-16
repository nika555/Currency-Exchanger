package com.example.currencyexchanger.data.remote.models

import com.example.currencyexchanger.domain.models.RateModel
import com.squareup.moshi.Json

data class RateDto(
    @Json(name = "base")
    val base: String?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "rates")
    val rates: List<Rate>
) {
    data class Rate(
        @Json(name = "id")
        val id: Int?,
        @Json(name = "currency")
        val currency: String?,
        @Json(name = "rate")
        val rate: Double?
    )

    fun toRateModel() = RateModel(
        base = base,
        rates = rates.map { RateModel.Rate(id = it.id, currency = it.currency, rate = it.rate) },
    )
}
