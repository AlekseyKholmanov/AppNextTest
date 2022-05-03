package com.example.appnext.models.ui

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val error: String): NetworkResult<Nothing>()
}