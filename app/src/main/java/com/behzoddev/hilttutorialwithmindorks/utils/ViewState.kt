package com.behzoddev.hilttutorialwithmindorks.utils

sealed class ViewState<out T> {
    data class OnSuccess<T>(val data: T) : ViewState<T>()
    data class OnFailure<T>(val data: T?, val isLoading: Boolean) : ViewState<Nothing>()
    data class OnError<T>(val data: T?, val message: String?) : ViewState<Nothing>()

    companion object {
        fun <T> isSuccess(data: T) = OnSuccess(data)
        fun <T> isLoading(data: T?, isLoading: Boolean) = OnFailure(data, isLoading)
        fun <T> isError(data: T?, message: String) = OnError(data, message)
    }

}