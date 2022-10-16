package com.example.currencyexchanger.domain.usecases

import com.example.currencyexchanger.domain.repositories.RatesRepository
import javax.inject.Inject

class LoadRatesUseCase @Inject constructor(
    private val repository: RatesRepository
) {
    suspend operator fun invoke() = repository.getRates()
}