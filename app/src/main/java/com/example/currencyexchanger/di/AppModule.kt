package com.example.currencyexchanger.di

import com.example.currencyexchanger.data.remote.services.CurrencyAccountService
import com.example.currencyexchanger.data.remote.services.RateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Provides
    @Singleton
    fun retrofitClientProvide(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun currencyAccountServiceProvide(retrofit: Retrofit): CurrencyAccountService {
        return retrofit.create(CurrencyAccountService::class.java)
    }


    @Provides
    @Singleton
    fun ratesServiceProvide(retrofit: Retrofit): RateService {
        return retrofit.create(RateService::class.java)
    }
}