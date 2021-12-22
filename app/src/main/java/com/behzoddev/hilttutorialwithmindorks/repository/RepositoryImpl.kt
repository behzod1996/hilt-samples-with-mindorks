package com.behzoddev.hilttutorialwithmindorks.repository

import android.util.Log
import com.behzoddev.hilttutorialwithmindorks.database.User
import com.behzoddev.hilttutorialwithmindorks.network.ApiService
import com.behzoddev.hilttutorialwithmindorks.utils.NetworkResult
import com.behzoddev.hilttutorialwithmindorks.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val webService: ApiService) : Repository {

    override suspend fun getUsers() : Flow<NetworkResult<List<User>>> = flow {
        Log.d("Debug","getUsers function in Repository is created")
        safeApiCall("Error") {
            webService.getUsers()
        }
    }
}