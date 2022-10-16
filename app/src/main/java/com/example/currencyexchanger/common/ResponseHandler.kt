package com.example.currencyexchanger.common

import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseHandler @Inject constructor() {

    fun <T> safeApiCall(call: suspend () -> Response<T>) = flow {
        emit(Resource.Loader(isLoading = true))
        try {
            val response = call()
            if (response.isSuccessful && response.body() != null)
                emit(Resource.Success(success = response.body()))
            else
                emit(Resource.Failure(error = response.errorBody()?.string()))
        } catch (t: Throwable) {
            emit(Resource.Failure(error = t.message))
        }
        emit(Resource.Loader(isLoading = false))
    }

}