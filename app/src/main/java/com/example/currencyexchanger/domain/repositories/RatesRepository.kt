package com.example.currencyexchanger.domain.repositories

import com.example.currencyexchanger.common.Resource
import com.example.currencyexchanger.domain.models.RateModel
import kotlinx.coroutines.flow.Flow

interface RatesRepository {
    suspend fun getRates(): Flow<Resource<RateModel>>

}