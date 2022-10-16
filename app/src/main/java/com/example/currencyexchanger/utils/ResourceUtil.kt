package com.example.currencyexchanger.utils

import com.example.currencyexchanger.common.Resource

object ResourceUtil {

    fun <T : Any> getResourceType(
        resource: Resource<T>,
        onLoading: ((Boolean) -> Unit),
        onSuccess: (T) -> Unit,
        onError: ((Resource.Failure<T>) -> Unit)? = null,
    ) {
        when (resource) {
            is Resource.Loader -> {
                onLoading.invoke(resource.isLoading)
            }
            is Resource.Success -> {
                resource.success?.let { onSuccess.invoke(it) }
            }
            is Resource.Failure -> {
                onError?.invoke(resource)
            }
        }
    }
}