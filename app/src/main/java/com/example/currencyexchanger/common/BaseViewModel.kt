package com.example.currencyexchanger.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyexchanger.utils.ResourceUtil.getResourceType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun <T : Any> handleResponse(
        apiCall: suspend () -> Flow<Resource<T>>,
        onLoading: ((Boolean) -> Unit)? = null,
        onSuccess: (T) -> Unit,
        onError: ((String) -> Unit)? = null,
    ) {
        viewModelScope.launch {
            apiCall.invoke().collect { resource ->
                getResourceType(
                    resource = resource,
                    onLoading = {
                        onLoading?.invoke(resource.isLoading)
                    },
                    onSuccess = {
                        onSuccess.invoke(it)
                    },
                    onError = {
                        it.error?.let { it1 -> onError?.invoke(it1) }
                    }
                )
            }
        }
    }
}