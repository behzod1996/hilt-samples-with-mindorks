package com.behzoddev.hilttutorialwithmindorks.utils

import com.behzoddev.hilttutorialwithmindorks.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retryWhen
import retrofit2.Response
import java.io.IOException


val dispatcherIO = Dispatchers.IO
suspend fun <T: Any> safeApiCall(
    errorMessage: String = "Error",
    allowRetries: Boolean = true,
    numberOfRetries: Int = 2,
    networkApiCall: suspend () -> Response<T>
) : Flow<NetworkResult<T>> {
    var duration = 1000L
    val delayFactor =2

    return flow {
        val response = networkApiCall()
        if(response.isSuccessful){
            response.body()?.let { result: T->
                emit(NetworkResult.OnSuccess(result))
            }
                ?: emit(NetworkResult.OnFailure(IOException("Api call successful, but empty response body")))
            return@flow
        }
        emit(
            NetworkResult.OnFailure(IOException("Api call failed with error - ${response.errorBody()
                ?.string() ?: errorMessage}")))
            return@flow
    }.catch { e->
        emit(NetworkResult.OnFailure(IOException("Exception is occured during Api Call: ${e.message}")))
        return@catch
    }.retryWhen{ cause: Throwable, attempt: Long ->
        if(!allowRetries || attempt > numberOfRetries || cause !is IOException) return@retryWhen false
        delay(duration)
        duration *= delayFactor
        return@retryWhen true
    }.flowOn(dispatcherIO)
}

suspend fun <T: Any> getViewStateFlowForNetworkCall(IOOperation: suspend () -> Flow<NetworkResult<T>>) =
    flow {
        emit(Resource.OnLoading(true))
        IOOperation().map {
            when(it) {
                is NetworkResult.OnSuccess -> Resource.OnSuccess(it.data)
                is NetworkResult.OnFailure -> Resource.OnError(it.throwable)
                is NetworkResult.OnLoading -> Resource.OnLoading(it.isLoading)
            }
        }.collect {
            emit(it)
        }
        emit(Resource.OnLoading(false))
    }.flowOn(dispatcherIO)