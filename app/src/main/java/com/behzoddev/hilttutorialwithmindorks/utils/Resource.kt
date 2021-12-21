package com.behzoddev.hilttutorialwithmindorks.utils

sealed class Resource <out T: Any> {
    data class OnSuccess<out T: Any>(val data : T) : Resource<T>()
    data class OnLoading(val isLoading: Boolean) : Resource<Nothing>()
    data class OnError(val throwable: Throwable) : Resource<Nothing>()
}