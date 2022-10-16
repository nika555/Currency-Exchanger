package com.example.currencyexchanger.domain.usecases

import com.example.currencyexchanger.domain.repositories.CurrencyAccountRepository
import javax.inject.Inject

class CurrencyAccountUseCase @Inject constructor(
    private val repository: CurrencyAccountRepository
) {
    suspend operator fun invoke() = repository.getAccountCurrency()

}