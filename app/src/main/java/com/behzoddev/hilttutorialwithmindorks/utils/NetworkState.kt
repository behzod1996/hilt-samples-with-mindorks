package com.behzoddev.hilttutorialwithmindorks.utils

sealed class NetworkState<out T> {
    data class OnSuccess<T>(val data: T?) : NetworkState<T>()
    data class OnFailure<T>(val data: T?, val message: String) : NetworkState<Nothing>()
    data class OnLoading<T>(val data: T?, val isLoading: Boolean) : NetworkState<Nothing>()

    companion object {
        fun <T> isSuccess(data: T?) = OnSuccess(data)
        fun <T> isFailure(data: T?, message: String) = OnFailure(data, message)
        fun <T> isLoading(data: T?, isLoading: Boolean) = OnLoading(data, isLoading)
    }
}