package com.example.currencyexchanger.data.remote.services

import com.example.currencyexchanger.utils.ApiUtil
import com.example.currencyexchanger.data.remote.models.RateDto
import retrofit2.Response
import retrofit2.http.GET

interface RateService {
    @GET(ApiUtil.RATES)
    suspend fun rates(): Response<RateDto>
}