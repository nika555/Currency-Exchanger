package com.example.currencyexchanger.data.repositories

import com.example.currencyexchanger.common.Resource
import com.example.currencyexchanger.common.ResponseHandler
import com.example.currencyexchanger.common.mapSuccess
import com.example.currencyexchanger.data.remote.services.RateService
import com.example.currencyexchanger.domain.models.RateModel
import com.example.currencyexchanger.domain.repositories.RatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val api: RateService,
    private val handler: ResponseHandler
) : RatesRepository {

    override suspend fun getRates(): Flow<Resource<RateModel>> {
        return handler.safeApiCall {
            api.rates()
        }.map { resource ->
            resource.mapSuccess { dto ->
                dto.toRateModel()
            }
        }
    }
}