package com.example.pexelsapp.presentation.screen.root

import androidx.lifecycle.ViewModel
import com.example.pexelsapp.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel: ViewModel() {

    protected val errorHandler = CoroutineExceptionHandler { _, error ->
        _errorState.value = when (error) {
            is HttpException -> when (error.code()) {
                400 -> R.string.error_400
                401 -> R.string.error_401
                403 -> R.string.error_403
                404 -> R.string.error_404
                429 -> R.string.error_429
                500 -> R.string.error_500
                502 -> R.string.error_502
                else -> R.string.unknown_error
            }

            is UnknownHostException -> R.string.error_unknown_host
            is ConnectException -> R.string.error_connect
            is SocketTimeoutException -> R.string.error_timeout
            else -> R.string.unknown_error
        }
    }

    protected open val _errorState = MutableStateFlow<Int?>(null)
    val errorState: StateFlow<Int?> = _errorState



}